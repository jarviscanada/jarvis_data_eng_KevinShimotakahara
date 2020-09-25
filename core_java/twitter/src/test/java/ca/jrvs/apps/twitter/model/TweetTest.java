package ca.jrvs.apps.twitter.model;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class TweetTest {
  @Test
  public void jsonNonsense() throws Exception{
    ObjectMapper objectMapper = new ObjectMapper();

    String tweet =
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
    Tweet lolz = objectMapper.readValue(tweet, Tweet.class);
  }

}