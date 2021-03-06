package ca.jrvs.apps.trading.dao;

import static ca.jrvs.apps.trading.util.JsonUtil.toObjectFromJson;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import ca.jrvs.apps.trading.model.IexQuote;

/**
 * MarketDataDao is responsible for getting Quotes from IEX
 */
@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

  private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
  private final String IEX_BATCH_URL;
  private final String token;
  private final String host;

  private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
  private HttpClientConnectionManager httpClientConnectionManager;

  @Autowired
  public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager,
                       MarketDataConfig marketDataConfig) {
    this.httpClientConnectionManager = httpClientConnectionManager;
    token = marketDataConfig.getToken();
    host = marketDataConfig.getHost();
    IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + token;
  }

  /**
   * Get an IexQuote (helper method)
   * @param ticker
   * @throws IllegalArgumentException if a given ticker is invalid
   * @throws DataRetrievalFailureException if HTTP request failed
   */
  @Override
  public Optional<IexQuote> findById(String ticker) {
    Optional<IexQuote> iexQuote;
    List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));

    if(quotes.size() == 0) {
      return Optional.empty();
    } else if (quotes.size() == 1) {
      iexQuote = Optional.of(quotes.get(0));
    } else {
      throw new DataRetrievalFailureException("Unexpected number of quotes");
    }
    return iexQuote;
  }

  /**
   * Get quotes from IEX
   * @param tickers is a list of tickers
   * @return a list of IexQuote objects
   * @throws IllegalArgumentException if any ticker is invalid or tickers is empty
   * @throws DataRetrievalFailureException if HTTP request failed
   */
  @Override
  public List<IexQuote> findAllById(Iterable<String> tickers) throws IllegalArgumentException {
    // build URI, pass it to executeHttpGet
    Iterator<String> it = tickers.iterator();
    StringBuilder symbolsField = new StringBuilder();
    List<IexQuote> iexQuotes = new LinkedList<>();
    while(it.hasNext()){
      symbolsField.append(it.next().toLowerCase() + ",");
    }
    if(symbolsField != null){
      symbolsField.deleteCharAt(symbolsField.length() - 1);
      String url = buildURL(symbolsField.toString());
      Optional<String> jsonStr = null;
      try {
        jsonStr = executeHttpGet(url);
      } catch (URISyntaxException e) {
        throw new IllegalArgumentException("Incorrect URI syntax passed to Http client",e);
      } catch (IOException e) {
        throw new IllegalArgumentException("Failed to receive Http Response from IEX Server",e);
      }
      //Object will be a nested hashmap; the innermost hashmaps are what translate to IexQuote model
      JSONObject jo = new JSONObject(jsonStr.get());
      JSONObject nestedJo = null;
      JSONObject nestedNestedJo = null;
      it = tickers.iterator();
      while(it.hasNext()){
        try {
          nestedJo = (JSONObject) jo.get(it.next().toUpperCase());
          nestedNestedJo = (JSONObject) nestedJo.get("quote");
          iexQuotes.add(toObjectFromJson(nestedNestedJo.toString(), IexQuote.class));
        } catch (IOException e) {
          throw new RuntimeException("Failed to convert Json to IexQuote",e);
        } catch (Exception e) {
          throw new IllegalArgumentException("Not all expected quotes returned. Check if tickers are valid.",e);
        }
      }
      return iexQuotes;
    }
    return null;
  }

  private String buildURL(String symbolsField){
    return host + "/stock/market/batch?symbols=" + symbolsField + "&types=quote&token=" + token;
  }

  /**
   * Execute a get and return http entity/body as a string
   * use EntityUtils.toString to process HTTP entity
   *
   * @param url resource URL
   * @return http response body or Optional.empty for 404 response
   * @throws DataRetrievalFailureException if HTTP failed or status code is unexpected
   */
  private Optional<String> executeHttpGet(String url) throws URISyntaxException, IOException {
    //will need to get a http response using a http client from the connection manager
    //then extract its response body as a JSON string, which will be converted to POJO
    //by other methods

    HttpGet request = new HttpGet(new URI(url));

    //parseResponseBody(HttpResponse response, Integer expectedStatusCode) //EntityUtils.toString(response.getEntity())
    CloseableHttpClient httpClient = getHttpClient();
    HttpResponse response = httpClient.execute(request);

    return Optional.of(parseResponseBody(response,200));
  }

  public String parseResponseBody(HttpResponse response, Integer expectedStatusCode){

    //Check response status
    int status = response.getStatusLine().getStatusCode();
    if(status != expectedStatusCode){
      try{
        System.out.println(EntityUtils.toString(response.getEntity()));
      }catch (IOException e){
        System.out.println("Response has no entity");
      }
      throw new RuntimeException("Unexpected HTTP status: " + status);
    }

    if(response.getEntity() == null){
      throw new RuntimeException("Empty reponse body");
    }

    String jsonStr;
    try{
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch(IOException e){
      throw new RuntimeException("Failed to convert entity to String", e);
    }
    return jsonStr;
  }

  /**
   * Borrow a HTTP client from the httpClientConnectionManager
   * @return a httpClient
   */
  private CloseableHttpClient getHttpClient(){
    return HttpClients.custom()
            .setConnectionManager(httpClientConnectionManager)
            //prevent connectionManager shutdown when calling httpClient.close()
            .setConnectionManagerShared(true)
            .build();
  }

  @Override
  public <S extends IexQuote> S save(S s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public boolean existsById(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public Iterable<IexQuote> findAll() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public long count() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteById(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void delete(IexQuote iexQuote) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends IexQuote> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll() {
    throw new UnsupportedOperationException("Not implemented");
  }
}
