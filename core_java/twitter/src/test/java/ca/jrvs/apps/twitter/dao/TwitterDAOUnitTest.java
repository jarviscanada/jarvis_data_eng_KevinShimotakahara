package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDAOUnitTest {

  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDAO dao;

  @Test
  public void showTweet() throws Exception {
    //test failed request
    String text = "The time is " + System.currentTimeMillis();
    double lat = 45.3055448, lon = -75.908182;

    //exception is expected here
    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
    try{
      dao.create(TweetUtil.buildTweet(text,lon,lat));
      fail();
    } catch (RuntimeException e){
      assertTrue(true);
    }

    //Test happy path
    //however, we don't want to call parseReponseBody
    String tweetJsonStr =
        "{\"created_at\": \"Thu Sep 24 12:51:50 +0000 2020\","
            + "\"id\": 1309113314534993922,"
            + "\"id_str\": \"1309113314534993922\","
            + "\"text\": \"Dan Schnieder why\","
            + "\"truncated\": false,"
            + "\"entities\": {"
            + "\"hashtags\": [],"
            + "\"user_mentions\": []"
            +"},"
            + "\"coordinates\": {"
            +  "\"type\": \"Point\","
            +  "\"coordinates\": [-75.908182, 45.3055448]}"
            + "}";

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDAO spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
    //mock parseResponseBody
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
    Tweet tweet = spyDao.create(TweetUtil.buildTweet(text,lon,lat));
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
}

  @Test
  public void findById() throws Exception{
    //exception is expected here
    when(mockHelper.httpGet(isNotNull())).thenThrow(new RuntimeException("mock"));
    try{
      dao.findById("1309113314534993922");
      fail();
    }catch(RuntimeException e){
      assertTrue(true); //dummy assert to signal that the mock threw the RuntimeException
    }

    //Test happy path
    //however, we don't want to call parseReponseBody
    String tweetJsonStr =
        "{\"created_at\": \"Thu Sep 24 12:51:50 +0000 2020\","
            + "\"id\": 1309113314534993922,"
            + "\"id_str\": \"1309113314534993922\","
            + "\"text\": \"Dan Schnieder why\","
            + "\"truncated\": false,"
            + "\"entities\": {"
            + "\"hashtags\": [],"
            + "\"user_mentions\": []"
            +"},"
            + "\"coordinates\": {"
            +  "\"type\": \"Point\","
            +  "\"coordinates\": [-75.908182, 45.3055448]}"
            + "}";
    when(mockHelper.httpGet(isNotNull())).thenReturn(null);
    TwitterDAO spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
    //mock parseResponseBody
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
    Tweet tweet = spyDao.findById("1309113314534993922");
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
    assertEquals(expectedTweet,tweet);
  }

  @Test
  public void deleteById() throws Exception {
    //exception is expected here
    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      dao.deleteById("1309113314534993922");
      fail();
    } catch (RuntimeException e) {
      assertTrue(true); //dummy assert to signal that the mock threw the RuntimeException
    }

    //Test happy path
    //however, we don't want to call parseReponseBody
    String tweetJsonStr =
        "{\"created_at\": \"Thu Sep 24 12:51:50 +0000 2020\","
            + "\"id\": 1309113314534993922,"
            + "\"id_str\": \"1309113314534993922\","
            + "\"text\": \"Dan Schnieder why\","
            + "\"truncated\": false,"
            + "\"entities\": {"
            + "\"hashtags\": [],"
            + "\"user_mentions\": []"
            + "},"
            + "\"coordinates\": {"
            + "\"type\": \"Point\","
            + "\"coordinates\": [-75.908182, 45.3055448]}"
            + "}";

    TwitterDAO spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
    //mock parseResponseBody
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
    Tweet tweet = spyDao.findById("1309113314534993922");
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
    assertEquals(expectedTweet, tweet);
  }
}