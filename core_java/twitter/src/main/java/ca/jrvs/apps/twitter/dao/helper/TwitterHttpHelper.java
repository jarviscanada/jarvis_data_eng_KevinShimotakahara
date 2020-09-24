package ca.jrvs.apps.twitter.dao.helper;

import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI; //using URI object doesn't seem to work; I get a 401 error for POST commands
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;

public class TwitterHttpHelper implements HttpHelper {

  /**
   * Dependencies are specified as private member variables
   */
  private OAuthConsumer consumer;
  private HttpClient httpClient;

  /**Constructor
   * Setup dependencies using secrets
   *
   * @param consumerKey
   * @param consumerSecret
   * @param accessToken
   * @param tokenSecret
   */
  public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken, String tokenSecret){
    consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);
    /**
     * Default = single connection. Discuss source code if time permits
     */
    httpClient = HttpClientBuilder.create().build();
  }
//  @Override
//  public HttpResponse httpPost(URI uri) {
//    HttpPost request = new HttpPost(uri);
//    //sign the request (add headers)
//    try {
//      consumer.sign(request);
//    } catch (OAuthMessageSignerException e) {
//      e.printStackTrace();
//    } catch (OAuthExpectationFailedException e) {
//      e.printStackTrace();
//    } catch (OAuthCommunicationException e) {
//      e.printStackTrace();
//    }
//    HttpResponse response = null;
//    try {
//      response = httpClient.execute(request);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    return response;
//  }

//  @Override
//  public HttpResponse httpGet(URI uri) {
//    HttpGet request = new HttpGet(uri);
//    HttpResponse response = null;
//    try {
//      response = httpClient.execute(request);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    return response;
//  }

  /**
   * Execute a HTTP Post call
   *
   * @param uri
   * @return
   */
  @Override
  public HttpResponse httpPost(String uri) {
    try{
      return executeHttpRequest(HttpMethod.POST, uri,null);
    }catch(OAuthException | IOException e){
      throw new RuntimeException("Failed to execute", e);
    }
  }

  /**
   * Execute a HTTP Get call
   *
   * @param uri
   * @return
   */
  @Override
  public HttpResponse httpGet(String uri) {
    try{
      return executeHttpRequest(HttpMethod.GET, uri,null);
    }catch(OAuthException | IOException e){
      throw new RuntimeException("Failed to execute", e);
    }
  }

  private HttpResponse executeHttpRequest(HttpMethod method, String uri, StringEntity stringEntity)
    throws OAuthException, IOException {
    if(method == HttpMethod.GET){
      HttpGet request = new HttpGet(uri);
      consumer.sign(request);
      return httpClient.execute(request);
    }else if (method == HttpMethod.POST){
      HttpPost request = new HttpPost(uri);
      if(stringEntity != null){
        request.setEntity(stringEntity);
      }
      consumer.sign(request);
      return httpClient.execute(request);
    } else{
      throw new IllegalArgumentException("Unknown HTTP method: " + method.name());
    }
  }

  public static void main(String[] args) throws Exception {


//    String CONSUMER_KEY = System.getenv("consumerKey");
//    String CONSUMER_SECRET = System.getenv("consumerSecret");
//    String ACCESS_TOKEN = System.getenv("accessToken");
//    String TOKEN_SECRET = System.getenv("tokenSecret");
//
//    TwitterHttpHelper th = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
//
//    HttpResponse getTest = th.httpGet(URI.create("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=realDonaldTrump"));
//    System.out.println(JsonParser.toJson(getTest,true,false));
  }
}
