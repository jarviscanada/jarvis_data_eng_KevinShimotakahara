package ca.jrvs.apps.twitter.dao.helper;

import com.google.gdata.util.common.base.PercentEscaper;
import java.net.URI;
import junit.framework.TestCase;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import ca.jrvs.apps.twitter.dao.TwitterDAO;

public class TwitterHttpHelperTest extends TestCase {
  @Test
  public void testHttpPost() throws Exception{
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey+"|"+consumerSecret+"|"+accessToken+"|"+tokenSecret);
    //Create components
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey,consumerSecret,accessToken,tokenSecret);
    String status = "oh hai Tut mir nicht leid";
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    TwitterDAO dao = new TwitterDAO(httpHelper);
    HttpResponse response = httpHelper
        .httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=" + percentEscaper.escape(status)));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }
}