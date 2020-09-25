package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
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
    Tweet init = new Tweet();
    init.setText("You have good reason to feel existential dread");
    Coordinates coords = new Coordinates();
    double[] thisIsVerySyntaxHeavy = {45.3055448,-75.908182};
    coords.setCoordinates(thisIsVerySyntaxHeavy);
    init.setCoordinates(coords);
    Tweet thing = tDAO.create(init);
    assertEquals("You have good reason to feel existential dread",thing.getText());
  }
}