package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwitterCLIApp {

  public static final String USAGE = "USAGE: TwitterCLIApp post|show|delete [options]";

  private Controller controller;
  @Autowired
  public TwitterCLIApp(Controller controller){ this.controller = controller;}

  public static void main(String[] args) {
    //Get secrets from environment variables
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    //set up dependencies
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey,consumerSecret,
        accessToken,tokenSecret);
    CrdDao dao = new TwitterDAO(httpHelper);
    Service service = new TwitterService(dao);
    Controller controller = new TwitterController(service);
    TwitterCLIApp app = new TwitterCLIApp(controller);
    app.run(args);
  }

  public void run(String[] args){
    switch (args[0]) {
      case "post":
        printTweet(controller.postTweet(args));
        break;
      case "show":
        printTweet(controller.showTweet(args));
        break;
      case "delete":
        controller.deleteTweet(args).forEach(this::printTweet);
        break;
      default:
        throw new RuntimeException(USAGE);
    }
  }

  private void printTweet(Tweet tweet){
   try{
     System.out.println(JsonUtil.toPrettyJson(tweet));
   } catch (JsonProcessingException e){
     throw new RuntimeException("Unable to convert tweet object to string", e);
   }
  }
}