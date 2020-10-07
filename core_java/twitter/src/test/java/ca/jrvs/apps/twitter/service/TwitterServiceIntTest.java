package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {
  private TwitterDAO dao;
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
  public void integrationTest() throws Exception{
    //Make a tweet
    String text = "The time is " + System.currentTimeMillis();
    double lat = 45.3055448, lon = -75.908182;
    Tweet postTweet = TweetUtil.buildTweet(text,lat,lon);

    //Post a tweet
    TwitterService twitterService = new TwitterService(dao);
    Tweet tweet = twitterService.postTweet(postTweet);
    assertEquals(text, tweet.getText());

    //Show a tweet (text only)
    String[] fields = {"text"};
    Tweet showTweet = twitterService.showTweet(tweet.getId_str(),fields);

    System.out.println(JsonUtil.toPrettyJson(showTweet));

    //Make another tweet and post
    String text2 = "The time is " + System.currentTimeMillis();
    double lat2 = 45.3055448, lon2 = -75.908182;
    Tweet postTweet2 = TweetUtil.buildTweet(text2,lat2,lon2);

    Tweet tweet2 = twitterService.postTweet(postTweet2);
    assertEquals(text2, tweet2.getText());

    //Delete the tweets you just made
    String[] ids = {tweet.getId_str(),tweet2.getId_str()};
    List<Tweet> tweets = new LinkedList<>();
    tweets.addAll(twitterService.deleteTweets(ids));

    assertEquals(tweets.get(0).getId_str(),ids[0]);
    assertEquals(tweets.get(1).getId_str(),ids[1]);
  }
  @Test(expected = NumberFormatException.class)
  public void testBadNumber() throws Exception{
    //Make a tweet
    String text = "The time is " + System.currentTimeMillis();
    double lat = 45.3055448, lon = -75.908182;
    Tweet postTweet = TweetUtil.buildTweet(text,lat,lon);

    //Post a tweet
    TwitterService twitterService = new TwitterService(dao);
    Tweet tweet = twitterService.postTweet(postTweet);
    assertEquals(text, tweet.getText());

    //Show a tweet (text only)
    String[] fields = {"text"};
    Tweet showTweet = twitterService.showTweet(tweet.getId_str()+"lololol",fields);
    System.out.println(JsonUtil.toPrettyJson(showTweet));
  }
  @Test(expected = RuntimeException.class)
  public void testBadNumber2() throws Exception{
    //Make a tweet
    String text = "The time is " + System.currentTimeMillis();
    double lat = 45.3055448, lon = -75.908182;
    Tweet postTweet = TweetUtil.buildTweet(text,lat,lon);

    //Post a tweet
    TwitterService twitterService = new TwitterService(dao);
    Tweet tweet = twitterService.postTweet(postTweet);
    assertEquals(text, tweet.getText());

    //Delete the tweets you just made
    String[] ids = {tweet.getId_str()};
    List<Tweet> tweets = new LinkedList<>();
    tweets.addAll(twitterService.deleteTweets(ids));

    //Try to show the tweet you just deleted (text only)
    String[] fields = {"text"};
    Tweet showTweet = twitterService.showTweet(tweet.getId_str(),fields);
    System.out.println(JsonUtil.toPrettyJson(showTweet));
  }
  @Test(expected = RuntimeException.class)
  public void testBadCoordinate() {
    //Make a tweet
    String text = "The time is " + System.currentTimeMillis();
    double lat = 475.3055448, lon = -75.908182;
    Tweet postTweet = TweetUtil.buildTweet(text,lat,lon);

    //Post a tweet
    TwitterService twitterService = new TwitterService(dao);
    Tweet tweet = twitterService.postTweet(postTweet);
    assertEquals(text, tweet.getText());
  }

}