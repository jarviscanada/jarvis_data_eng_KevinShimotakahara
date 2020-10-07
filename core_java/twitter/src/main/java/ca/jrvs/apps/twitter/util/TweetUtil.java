package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;

public class TweetUtil {
  public static Tweet buildTweet(String text, double lat, double lon){
    Tweet init = new Tweet();
    init.setText(text);
    Coordinates coords = new Coordinates();
    double[] thisIsVerySyntaxHeavy = {lat,lon};
    coords.setCoordinates(thisIsVerySyntaxHeavy);
    init.setCoordinates(coords);
    return init;
  }
}
