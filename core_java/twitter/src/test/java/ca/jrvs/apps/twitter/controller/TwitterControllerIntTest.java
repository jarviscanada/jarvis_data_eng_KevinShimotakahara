package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.JsonUtil;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterControllerIntTest {
  private TwitterService service;
  @Before
  public void setup(){
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey+"|"+consumerSecret+"|"+accessToken+"|"+tokenSecret);
    //set up dependency
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey,consumerSecret,accessToken,tokenSecret);
    TwitterDAO dao = new TwitterDAO(httpHelper);
    this.service = new TwitterService(dao);
  }
  @Test
  public void postTweet() {
    TwitterController twitterController = new TwitterController(this.service);
    String text = "The time is " + System.currentTimeMillis();
    String[] args = {"post",text,"45.12:-5.12"};
    Tweet tweet = twitterController.postTweet(args);
    assertEquals(tweet.getText(),text);
  }

  @Test
  public void showTweet() throws Exception{
    TwitterController twitterController = new TwitterController(this.service);
    String id = "1309113314534993922";
    String fields = "[text,id_str]";
    String[] args = {"show",id,fields};
    Tweet tweet = twitterController.showTweet(args);
    assertEquals(id,tweet.getId_str());
    System.out.println(JsonUtil.toPrettyJson(tweet));
  }

  @Test
  public void deleteTweet() {
    TwitterController twitterController = new TwitterController(this.service);
    String text = "The time is " + System.currentTimeMillis();
    String[] args = {"post",text,"45.12:-5.12"};
    Tweet tweet1 = twitterController.postTweet(args);

    StringBuilder ids = new StringBuilder("["+tweet1.getId_str());

    text = "The time is " + System.currentTimeMillis();
    String[] args2 = {"post",text,"45.12:-5.12"};
    Tweet tweet2 = twitterController.postTweet(args2);

    ids.append(","+tweet2.getId_str()+"]");

    String fields = "[text,id_str]";
    String[] args3 = {"delete",ids.toString()};
    List<Tweet> tweets = twitterController.deleteTweet(args3);
    assertEquals(tweet1.getId_str(),tweets.get(0).getId_str());
    assertEquals(tweet2.getId_str(),tweets.get(1).getId_str());
  }
}