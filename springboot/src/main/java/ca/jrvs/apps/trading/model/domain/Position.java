package ca.jrvs.apps.trading.model.domain;

public class Position implements Entity<Integer> {
  private Integer account_id;
  private Integer position;
  private String ticker;

  public Integer getAccount_id() {
    return account_id;
  }

  public void setAccount_id(Integer account_id) {
    this.account_id = account_id;
  }

  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  @Override
  public Integer getId() {
    return account_id;
  }

  @Override
  public void setId(Integer integer) {
    account_id = integer;
  }
}
