package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {
  @Mock
  CrdDao dao;

  @InjectMocks
  TwitterService service;

  @Test
  public void postTweet() throws Exception {
    when(dao.create(any())).thenReturn(new Tweet());
    service.postTweet(TweetUtil.buildTweet("test",0.0,50.0));
  }

  @Test(expected = RuntimeException.class)
  public void postTweetTestLonValidation() throws Exception {
    //testing if IOException is thrown when bad lon coordinate passed
    service.postTweet(TweetUtil.buildTweet("test",0.0,500.0));
  }

  @Test(expected = RuntimeException.class)
  public void postTweetTestLatValidation() throws Exception {
    //testing if IOException is thrown when bad lat coordinate passed
    service.postTweet(TweetUtil.buildTweet("test",11110.0,50.0));
  }

  @Test(expected = RuntimeException.class)
  public void postTweetTestStatusValidation() throws Exception {
    //testing if IOException is thrown when tweet is larger than 140 characters
    when(dao.create(any())).thenReturn(new Tweet());

    //build 141 character String
    StringBuilder largeStatus = new StringBuilder();
    for(; largeStatus.length() <= 141; largeStatus.append("a"))
    service.postTweet(TweetUtil.buildTweet(largeStatus.toString(),0.0,50.0));
  }

  @Test
  public void showTweet() throws Exception {
    //make sure nothing weird happens at service layer when you pass a valid argument to showTweet
    when(dao.findById(any())).thenReturn(new Tweet());
    service.showTweet("1309113314534993922",null);

    //test again, but testing the "filterTweet" functionality
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

    //mock dao layer to always spit out the dummy tweet
    Tweet expectedTweetFromHttpClient = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
    when(dao.findById(isNotNull())).thenReturn(expectedTweetFromHttpClient);

    //call service, check if text field is still there but others are null
    String[] fields = {"text"};
    Tweet filteredTweet = service.showTweet("1309113314534993922",fields);
    assertEquals(filteredTweet.getText(),"Dan Schnieder why");
    assertEquals(filteredTweet.getId(),null);
    assertEquals(filteredTweet.getEntities(),null);
  }

  @Test
  public void deleteTweets() throws Exception {
    //business as usual
    String[] ids = {"123","456"};
    List<Tweet> tweets = new LinkedList<>();
    when(dao.deleteById(any())).thenReturn(new Tweet());
    tweets.addAll(service.deleteTweets(ids));
  }
  @Test(expected = RuntimeException.class)
  public void deleteTweetsExceptionTest() throws Exception{
    //bad string ID
    String[] idsBad = {"fight","the","power"};
    List<Tweet> tweets = new LinkedList<>();
    tweets.addAll(service.deleteTweets(idsBad));
  }
}