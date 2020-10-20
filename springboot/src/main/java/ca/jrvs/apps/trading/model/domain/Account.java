package ca.jrvs.apps.trading.model.domain;

public class Account implements Entity<Integer> {
  private Integer id;
  private Integer trader_id;
  private Float amount;

  public Integer getTrader_id() {
    return trader_id;
  }

  public void setTrader_id(Integer trader_id) {
    this.trader_id = trader_id;
  }

  public Float getAmount() {
    return amount;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer integer) {
    this.id = integer;
  }
}
