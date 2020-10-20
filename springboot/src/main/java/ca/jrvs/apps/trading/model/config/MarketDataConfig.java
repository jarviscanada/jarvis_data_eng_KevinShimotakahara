package ca.jrvs.apps.trading.model.config;

public class MarketDataConfig {
  private String host = "https://cloud.iexapis.com/v1/";
  private String token = System.getenv("IEX_PUB_TOKEN");

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
