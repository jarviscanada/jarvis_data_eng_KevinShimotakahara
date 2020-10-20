package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.IexQuote;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import junit.framework.TestCase;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
public class MarketDataDaoIntTest {
  private MarketDataDao dao;
  private IexQuote iexQuoteSaved;

  @Before
  public void init(){
    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    cm.setMaxTotal(50);
    cm.setDefaultMaxPerRoute(50);
    MarketDataConfig marketDataConfig = new MarketDataConfig();
    marketDataConfig.setHost("https://cloud.iexapis.com/v1/");
    marketDataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));

    dao = new MarketDataDao(cm, marketDataConfig);

    String ticker = "AAPL";
    IexQuote iexQuoteSaved = dao.findById(ticker).get();
  }
  @Test
  public void findIexQuotesByTickers() throws IOException {
    //happy path
    List<IexQuote> quoteList = dao.findAllById(Arrays.asList("AAPL","FB"));
    assertEquals(2,quoteList.size());
    assertEquals("AAPL", quoteList.get(0).getSymbol());

    //sad path
    try{
      dao.findAllById(Arrays.asList("AAPL","FB2"));
      fail();
    } catch(IllegalArgumentException e) {
      assertTrue(true);
    } catch(Exception e){
      fail();
    }
  }

  @Test
  public void findByTicker() {
    String ticker = "AAPL";
    IexQuote iexQuote = dao.findById(ticker).get();
    assertEquals(ticker, iexQuote.getSymbol());
  }

  @Test
  public void findById() {
    String ticker = "AAPL";
    IexQuote iexQuote = dao.findById(ticker).get();
    assertEquals(ticker, iexQuote.getSymbol());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void save(){
    dao.save(iexQuoteSaved);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void saveAll(){
    List<IexQuote> l = new LinkedList<>();
    l.add(iexQuoteSaved);
    dao.saveAll(l);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void existsById(){
    dao.existsById("iexQuoteSaved");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void findAll(){
    dao.findAll();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void count(){
    dao.count();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void deleteById(){
    dao.deleteById("hi");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void delete(){
    dao.delete(iexQuoteSaved);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void deleteAll(){
    List<IexQuote> l = new LinkedList<>();
    l.add(iexQuoteSaved);
    dao.deleteAll(l);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void deleteAll2(){
    dao.deleteAll();
  }
}