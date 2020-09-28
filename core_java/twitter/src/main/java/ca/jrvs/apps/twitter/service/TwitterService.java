package ca.jrvs.apps.twitter.service;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.FourOFourNotFoundException;
import ca.jrvs.apps.twitter.util.Showdelete;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TwitterService implements Service {
  private CrdDao dao;

  @Autowired
  public TwitterService(CrdDao dao) {this.dao = dao;}

  public Field[] tweetFields = Tweet.class.getDeclaredFields();
  /**
   * Validate and post a user input Tweet
   *
   * @param tweet tweet to be created
   * @return created tweet
   * @throws IllegalArgumentException if text exceed max number of allowed characters or lat/long
   *                                  out of range
   */
  @Override
  public Tweet postTweet(Tweet tweet) {
    //Business logic:
    //e.g. text length, lat/lon range
    try{
      validatePostTweet(tweet);
    }catch(IOException e){
      throw new RuntimeException("Invalid tweet. Make sure the data meets correct"
          + "formatting constraints.",e);
    }

    //create tweet via dao
    try {
      return (Tweet) dao.create(tweet);
    } catch (FourOFourNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Search a tweet by ID
   *
   * @param id     tweet id
   * @param fields set fields not in the list to null
   * @return Tweet object which is returned by the Twitter API
   * @throws IllegalArgumentException if id or fields param is invalid
   */
  @Override
  public Tweet showTweet(String id, String[] fields) {
    Tweet tweet = validateShowDeleteTweet(id,Showdelete.SHOW);
    if(fields == null)
      return tweet;
    return filterTweet(tweet,fields);
  }

  public Tweet filterTweet(Tweet tweet, String[] fields){
    List<String> fieldsList = Arrays.asList(fields);
    Arrays.stream(tweetFields).forEach(field -> {
      field.setAccessible(true);
      if (!(fieldsList.contains(field.getName()))) {
        try {
          field.set(tweet, null);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    });
    return tweet;
  }

  /**
   * Delete Tweet(s) by id(s).
   *
   * @param ids tweet IDs which will be deleted
   * @return A list of Tweets
   * @throws IllegalArgumentException if one of the IDs is invalid.
   */
  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    List<Tweet> tweetList = new LinkedList<>();
    Arrays.stream(ids).forEach(id -> tweetList.add(validateShowDeleteTweet(id,Showdelete.DELETE)));
    return tweetList;
  }

  public Tweet validateShowDeleteTweet(String id, Showdelete showdelete) throws NumberFormatException{
    //make sure string is a number
    Long.parseLong(id,10);
    Tweet tweet = new Tweet();
    try {
      if(showdelete == Showdelete.SHOW){
        tweet = (Tweet) dao.findById(id);
      }else{
        tweet = (Tweet) dao.deleteById(id);
      }
    } catch (FourOFourNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException("Tweet you requested does not exist. Use a valid tweet id.");
    }
    return tweet;
  }

  public void validatePostTweet(Tweet tweet) throws IOException {
    //Is tweet less than 140 characters?
    if(tweet.getText().length() > 140)
      throw new IOException("Tweet must be less than 140 characters");
    double lat = tweet.getCoordinates().getCoordinates()[0];
    double lon = tweet.getCoordinates().getCoordinates()[1];
    if(Math.abs(lat) > 90 || Math.abs(lon) > 180){
      throw new IOException("Invalid coordinates [lat="+lat+",long="+lon+"].\n "
          + "Latitude magnitude must be 90 degrees or less and "
          + "longitude magnitude must be 180 or less.");
    }
  }

}
