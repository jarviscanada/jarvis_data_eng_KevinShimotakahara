package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest {
  private TwitterDAO dao;
  private String id_str;
  @Before
  public void setup(){
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey+"|"+consumerSecret+"|"+accessToken+"|"+tokenSecret);
    //set up dependency
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey,consumerSecret,accessToken,tokenSecret);
    //pass dependency
    this.dao = new TwitterDAO(httpHelper);
  }

  @Test
  public void create() throws Exception {
    String text = "The time is " + System.currentTimeMillis();
    double lat = 45.3055448, lon = -75.908182;
    Tweet postTweet = TweetUtil.buildTweet(text,lat,lon);
    System.out.println(JsonUtil.toPrettyJson(postTweet));

    Tweet tweet = dao.create(postTweet);

    assertEquals(text, tweet.getText());

    assertNotNull(tweet.getCoordinates());
    assertEquals(2,tweet.getCoordinates().getCoordinates().length);
    assertEquals((Double) lat,(Double) tweet.getCoordinates().getCoordinates()[0]);
    assertEquals((Double) lon,(Double) tweet.getCoordinates().getCoordinates()[1]);
    //use tweet id just created by this test for next tests
    id_str = tweet.getId_str();
    Tweet tweet2 = dao.findById("13093120134314496");
    assertEquals(id_str, tweet2.getId_str());
    Tweet tweet3 = dao.deleteById(id_str);
    assertEquals(id_str, tweet3.getId_str());
  }

}