package ca.jrvs.apps.twitter.controller;

import static ca.jrvs.apps.twitter.util.TweetUtil.buildTweet;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.Arrays;
import java.util.List;
import org.springframework.util.StringUtils;

public class TwitterController implements Controller {
  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  private Service service;

  //@Autowired
  public TwitterController(Service service) {this.service = service;}

  /**
   * Parse user argument and post a tweet by calling service classes
   *
   * @param args
   * @return a posted tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public Tweet postTweet(String[] args) {
    if (args.length != 3)
      throw new IllegalArgumentException(
          "USAGE: TwitterCLIApp post \"tweet_text\" \"latitidue:longitude\""
      );

    String tweet_txt = args[1];
    String coord = args[2];
    String[] coordArray = coord.split(COORD_SEP);
    if(coordArray.length != 2 || StringUtils.isEmpty(tweet_txt))
      throw new IllegalArgumentException(
          "Invalid location format\nUSAGE: TwitterCLIApp post \"tweet_text\" \"latitidue:longitude\""
      );
    Double lat = null;
    Double lon = null;

    try {
      lat = Double.parseDouble(coordArray[0]);
      lon = Double.parseDouble(coordArray[1]);
    } catch (Exception e){
      throw new IllegalArgumentException(
      "Invalid location format\nUSAGE: TwitterCLIApp post \"tweet_text\" \"latitidue:longitude\""
      );
    }

    Tweet postTweet = buildTweet(tweet_txt,lon,lat);
    return service.postTweet(postTweet);
  }

  /**
   * Parse user argument and search a tweet by calling service classes
   *
   * @param args
   * @return a tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public Tweet showTweet(String[] args) {
    if (args.length != 3)
      throw new IllegalArgumentException(
        "Invalid input format\nUSAGE: TwitterApp show tweet_id [field1,fields2]");
    String id = args[1];
    StringBuilder fieldsStringBuilder = new StringBuilder(args[2]);
    //remove the outer brackets
    try{
      fieldsStringBuilder.deleteCharAt(0)
          .deleteCharAt(fieldsStringBuilder.lastIndexOf("]"));
    }catch(Exception e){
      throw new IllegalArgumentException(
          "Invalid fields format\nUSAGE: TwitterApp show tweet_id [field1,fields2]");
    }

    String fieldsString = fieldsStringBuilder.toString();
    //now can split by comma
    String[] fields = fieldsString.split(COMMA);
    Arrays.stream(fields).forEach(field -> field.trim());

    if(fields.length == 0 || StringUtils.isEmpty(id))
      throw new IllegalArgumentException(
          "Empty argument(s)\nUSAGE: TwitterApp show tweet_id [field1,fields2]");

    return service.showTweet(id,fields);
  }

  /**
   * Parse user argument and delete tweets by calling service classes
   *
   * @param args
   * @return a list of deleted tweets
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if (args.length != 2)
      throw new IllegalArgumentException(
          "Invalid input format\nUSAGE: TwitterApp delete [id1,id2,..]");

    StringBuilder idsStringBuilder = new StringBuilder(args[1]);
    //remove the outer brackets
    try{
      idsStringBuilder.deleteCharAt(0)
          .deleteCharAt(idsStringBuilder.lastIndexOf("]"));
    }catch(Exception e){
      throw new IllegalArgumentException(
          "Invalid ids format\nUSAGE: TwitterApp show tweet_id [field1,fields2]");
    }

    String idsString = idsStringBuilder.toString();
    //now can split by comma
    String[] ids = idsString.split(COMMA);
    Arrays.stream(ids).forEach(id -> id.trim());

    if(ids.length == 0){
      throw new IllegalArgumentException(
          "Ids array is empty\nUSAGE: TwitterApp show tweet_id [field1,fields2]");
    }
    return service.deleteTweets(ids);
  }
}
