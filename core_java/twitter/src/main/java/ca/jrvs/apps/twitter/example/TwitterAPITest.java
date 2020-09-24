package ca.jrvs.apps.twitter.example;

import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.util.Arrays;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

public class TwitterAPITest {
  private static String CONSUMER_KEY = System.getenv("consumerKey");
  private static String CONSUMER_SECRET = System.getenv("consumerSecret");
  private static String ACCESS_TOKEN = System.getenv("accessToken");
  private static String TOKEN_SECRET = System.getenv("tokenSecret");

  public static void main(String[] args) {
//    String CONSUMER_KEY = "334u62xIEa55EY8tRuVvXAKw1";
//    String CONSUMER_SECRET = "dsc5QOzvNhoHbFRvb3sUwcD2EJJ2Bi9f4xpuZZIvPol5VKpaFH";
//    String ACCESS_TOKEN = "1308468864750153731-Ye7fNxY4GC31xnWh8ombUNTj0xbi6i";
//    String TOKEN_SECRET = "qzIZRGxrYphm1owdsDucky2pMsHpehxEDIrLwdj8QwZtN";
    //setup oauth
    OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
    consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

    //create an HTTP GET request
    String status = "oh hai";
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    HttpPost request = new HttpPost(
        "https://api.twitter.com/1.1/statuses/update.json?status=" + percentEscaper.escape(status));

    //sign the request (add headers)
    try {
      consumer.sign(request);
    } catch (OAuthMessageSignerException e) {
      e.printStackTrace();
    } catch (OAuthExpectationFailedException e) {
      e.printStackTrace();
    } catch (OAuthCommunicationException e) {
      e.printStackTrace();
    }
    System.out.println("Http Request Headers:");
    Arrays.stream(request.getAllHeaders()).forEach(System.out::println);

    //send the request
    HttpClient httpClient = HttpClientBuilder.create().build();
    HttpResponse response = null;
    try {
      response = httpClient.execute(request);
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(response.getEntity());
  }
}
