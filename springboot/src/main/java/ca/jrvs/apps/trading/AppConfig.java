package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.controller.QuoteController;
import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.service.QuoteService;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.HttpClientConnection;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableTransactionManagement
public class AppConfig {

  private Logger logger = LoggerFactory.getLogger(AppConfig.class);

  @Bean
  public MarketDataConfig marketDataConfig(){
   return new MarketDataConfig();
  }

  @Bean
  public HttpClientConnectionManager httpClientConnectionManager(){
    return new PoolingHttpClientConnectionManager();
  }

  @Bean
  public DataSource dataSource() {
    String url = System.getenv("PSQL_URL");
    String user = System.getenv("PSQL_USER");
    String password = System.getenv("PSQL_PASSWORD");
    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setUrl(url);
    basicDataSource.setUsername(user);
    basicDataSource.setPassword(password);
    return basicDataSource;
  }


  /*@Bean
  public MarketDataDao marketDataDao(HttpClientConnectionManager httpClientConnectionManager,
                                     MarketDataConfig marketDataConfig){
    return new MarketDataDao(httpClientConnectionManager,marketDataConfig);
  }

  @Bean
  public QuoteService quoteService(QuoteDao quoteDao, MarketDataDao marketDataDao){
    return new QuoteService(quoteDao,marketDataDao);
  }

  @Bean
  public QuoteController quoteController(QuoteService quoteService) {
    return new QuoteController(quoteService);
  }*/
}
