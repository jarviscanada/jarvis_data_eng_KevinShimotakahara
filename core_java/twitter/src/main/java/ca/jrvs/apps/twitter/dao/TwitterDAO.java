package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Entities;
import ca.jrvs.apps.twitter.model.Hashtag;
import ca.jrvs.apps.twitter.model.UserMention;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
   * @param entity entity that to be created
   * @return created entity
   */
  @Override
  public Tweet create(Tweet entity) {
    //So this needs to get the tweet entity info from the http response that results
    //From the post request bearing the text and coordinates found in the input Tweet DTO
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    Coordinates coords = entity.getCoordinates();
    //Step 1: generate URI from Tweet data
    String uri = API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL + percentEscaper.escape(entity.getText())
                  + AMPERSAND + "lat" + EQUAL + coords.getCoordinates()[0]
                  + AMPERSAND + "long" + EQUAL + coords.getCoordinates()[1];
    //Step 2: send post request with httphelper
    HttpResponse response = httpHelper.httpPost(uri);

    //Step 3: convert response object to JSON
    String responseJSON = null;
    try {
      responseJSON = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      e.printStackTrace();
    }

    //Step 4: convert JSON to Tweet object, return tweet
    Tweet yerTweet = null;
    try {
      yerTweet = toObjectFromJson(responseJSON, Tweet.class);
    } catch (IOException e) {
      e.printStackTrace();
    }

     return yerTweet;
  }

  /**
   * Parse JSON string to an object
   * @param json JSON str
   * @param clazz object class
   * @param <T> Type
   * @return Object
   * @throws IOException
   */
  public static <T> T toObjectFromJson(String json, Class clazz) throws IOException {
    ObjectMapper m = new ObjectMapper();
    m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    return (T) m.readValue(json,clazz);
  }
  /**
   * Find an entity(Tweet) by its id
   *
   * @param s entity id
   * @return Tweet entity
   */
  @Override
  public Tweet findById(String s) {
    return null;
  }

  /**
   * Delete an entity(Tweet) by its ID
   *
   * @param s of the entity to be deleted
   * @return deleted entity
   */
  @Override
  public Tweet deleteById(String s) {
    return null;
  }
}
