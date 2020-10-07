package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.util.FourOFourNotFoundException;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TwitterDAO implements CrdDao<Tweet, String> {

  //URI constants
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy";
  //URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  //Response code
  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;

  @Autowired
  public TwitterDAO(HttpHelper httpHelper){this.httpHelper = httpHelper; }

  /**
   * Create an entity(Tweet) to the underlying storage
   *
   * @param tweet entity that to be created
   * @return created entity
   */
  @Override
  public Tweet create(Tweet tweet) throws FourOFourNotFoundException{
    //Construct URI
    URI uri;
    try{
      uri = getPostUri(tweet);
    } catch(URISyntaxException | UnsupportedEncodingException e){
      throw new IllegalArgumentException("Invalid tweet input", e);
    }

    //Execute HTTP Request
    HttpResponse response = httpHelper.httpPost(uri);

    //Validate response and extract response to Tweet object
    return parseResponseBody(response,HTTP_OK);
  }

  public URI getDeleteUri(String id_str) throws URISyntaxException, UnsupportedEncodingException{
    StringBuilder sb = new StringBuilder();
    sb.append(API_BASE_URI).append(DELETE_PATH).append("/")
        .append(URLEncoder.encode(id_str, StandardCharsets.UTF_8.name())).append(".json");
    return new URI(sb.toString());
  }

  public URI getGetUri(String id_str) throws URISyntaxException, UnsupportedEncodingException {
    StringBuilder sb = new StringBuilder();
    sb.append(API_BASE_URI).append(SHOW_PATH).append(QUERY_SYM);
    appendQueryParam(sb,"id", URLEncoder.encode(id_str, StandardCharsets.UTF_8.name()),true);
    return new URI(sb.toString());
  }

  public URI getPostUri(Tweet tweet) throws URISyntaxException, UnsupportedEncodingException {
    String text = tweet.getText();
    Double longitude = tweet.getCoordinates().getCoordinates()[0];
    Double latitude = tweet.getCoordinates().getCoordinates()[1];

    StringBuilder sb = new StringBuilder();
    sb.append(API_BASE_URI).append(POST_PATH).append(QUERY_SYM);
    appendQueryParam(sb,"status", URLEncoder.encode(text, StandardCharsets.UTF_8.name()),true);
    appendQueryParam(sb,"long",longitude.toString(),false);
    appendQueryParam(sb,"lat",latitude.toString(),false);

    return new URI(sb.toString());
  }

  private void appendQueryParam(StringBuilder sb, String key, String value, boolean firstParam) {
    if(firstParam){
      sb.append(key).append(EQUAL).append(value);
    }else
      sb.append(AMPERSAND).append(key).append(EQUAL).append(value);
  }

  public Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode)
      throws FourOFourNotFoundException {
    Tweet tweet = null;

    //Check response status
    int status = response.getStatusLine().getStatusCode();
    if(status != expectedStatusCode){
      try{
        System.out.println(EntityUtils.toString(response.getEntity()));
      }catch (IOException e){
        System.out.println("Response has no entity");
      }
      if(status == 404){
        throw new FourOFourNotFoundException("HTTP resource was not found (404 response).");
      }else{
        throw new RuntimeException("Unexpected HTTP status: " + status);
      }

    }

    if(response.getEntity() == null){
      throw new RuntimeException("Empty reponse body");
    }

    //Convert reponse entity to str
    String jsonStr;
    try{
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch(IOException e){
      throw new RuntimeException("Failed to convert entity to String", e);
    }

    //Deserialize JSON string to Tweet object
    try{
      tweet = JsonUtil.toObjectFromJson(jsonStr,Tweet.class);
    } catch(IOException e){
      throw new RuntimeException("Unable to convert JSON str to Object", e);
    }

    return tweet;
  }

  public String getPostUriStr(Tweet tweet) {
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        Coordinates coords = tweet.getCoordinates();
        return  API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL + percentEscaper.escape(tweet.getText())
                  + AMPERSAND + "lat" + EQUAL + coords.getCoordinates()[0]
                  + AMPERSAND + "long" + EQUAL + coords.getCoordinates()[1];
  }


  /**
   * Find an entity(Tweet) by its id
   *
   * @param s entity id
   * @return Tweet entity
   */
  @Override
  public Tweet findById(String s) throws FourOFourNotFoundException {
    URI uri;
    try{
      uri = getGetUri(s);
    } catch(URISyntaxException | UnsupportedEncodingException e){
      throw new IllegalArgumentException("Invalid tweet input", e);
    }

    //Execute HTTP Request
    HttpResponse response = httpHelper.httpGet(uri);

    //Validate response and extract response to Tweet object
    return parseResponseBody(response,HTTP_OK);
  }

  /**
   * Delete an entity(Tweet) by its ID
   *
   * @param s of the entity to be deleted
   * @return deleted entity
   */
  @Override
  public Tweet deleteById(String s) throws FourOFourNotFoundException{
    URI uri;
    try{
      uri = getDeleteUri(s);
    } catch(URISyntaxException | UnsupportedEncodingException e){
      throw new IllegalArgumentException("Invalid tweet input", e);
    }

    //Execute HTTP Request
    HttpResponse response = httpHelper.httpPost(uri);

    //Validate response and extract response to Tweet object
    return parseResponseBody(response,HTTP_OK);
  }
}
