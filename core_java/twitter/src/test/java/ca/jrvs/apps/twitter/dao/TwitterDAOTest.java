package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.util.FourOFourNotFoundException;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.junit.Test;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Entities;
import ca.jrvs.apps.twitter.model.Hashtag;
import ca.jrvs.apps.twitter.model.UserMention;

public class TwitterDAOTest {

  @Test
  public void create() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey,consumerSecret,accessToken,tokenSecret);
    TwitterDAO tDAO = new TwitterDAO(httpHelper);
    String text = "The time is " + System.currentTimeMillis();
    double lat = 45.3055448, lon = -75.908182;
    Tweet init = TweetUtil.buildTweet(text,lat,lon);
    Tweet thing = null;
    try {
      thing = tDAO.create(init);
    } catch (FourOFourNotFoundException e) {
      e.printStackTrace();
    }
    assertEquals(text,thing.getText());
  }
}