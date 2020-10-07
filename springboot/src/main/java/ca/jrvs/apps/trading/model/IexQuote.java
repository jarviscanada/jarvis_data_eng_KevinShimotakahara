package ca.jrvs.apps.trading.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * The root schema
 * <p>
 * The root schema comprises the entire JSON document.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "symbol",
    "companyName",
    "calculationPrice",
    "open",
    "openTime",
    "close",
    "closeTime",
    "high",
    "low",
    "latestPrice",
    "latestSource",
    "latestTime",
    "latestUpdate",
    "latestVolume",
    "volume",
    "iexRealtimePrice",
    "iexRealtimeSize",
    "iexLastUpdated",
    "delayedPrice",
    "delayedPriceTime",
    "oddLotDelayedPrice",
    "oddLotDelayedPriceTime",
    "extendedPrice",
    "extendedChange",
    "extendedChangePercent",
    "extendedPriceTime",
    "previousClose",
    "previousVolume",
    "change",
    "changePercent",
    "iexMarketPercent",
    "iexVolume",
    "avgTotalVolume",
    "iexBidPrice",
    "iexBidSize",
    "iexAskPrice",
    "iexAskSize",
    "marketCap",
    "week52High",
    "week52Low",
    "ytdChange",
    "peRatio",
    "lastTradeTime",
    "isUSMarketOpen"
})
public class IexQuote {

  /**
   * The symbol schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("symbol")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private String symbol = "";
  /**
   * The companyName schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("companyName")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private String companyName = "";
  /**
   * The calculationPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("calculationPrice")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private String calculationPrice = "";
  /**
   * The open schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("open")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Integer open = 0;
  /**
   * The openTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("openTime")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Integer openTime = 0;
  /**
   * The close schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("close")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double close = 0.0D;
  /**
   * The closeTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("closeTime")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Integer closeTime = 0;
  /**
   * The high schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("high")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double high = 0.0D;
  /**
   * The low schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("low")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double low = 0.0D;
  /**
   * The latestPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("latestPrice")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double latestPrice = 0.0D;
  /**
   * The latestSource schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("latestSource")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private String latestSource = "";
  /**
   * The latestTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("latestTime")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private String latestTime = "";
  /**
   * The latestUpdate schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("latestUpdate")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Integer latestUpdate = 0;
  /**
   * The latestVolume schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("latestVolume")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Integer latestVolume = 0;
  /**
   * The volume schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("volume")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Integer volume = 0;
  /**
   * The iexRealtimePrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexRealtimePrice")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double iexRealtimePrice = 0.0D;
  /**
   * The iexRealtimeSize schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexRealtimeSize")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Integer iexRealtimeSize = 0;
  /**
   * The iexLastUpdated schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexLastUpdated")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Integer iexLastUpdated = 0;
  /**
   * The delayedPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("delayedPrice")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double delayedPrice = 0.0D;
  /**
   * The delayedPriceTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("delayedPriceTime")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Integer delayedPriceTime = 0;
  /**
   * The oddLotDelayedPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("oddLotDelayedPrice")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double oddLotDelayedPrice = 0.0D;
  /**
   * The oddLotDelayedPriceTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("oddLotDelayedPriceTime")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Integer oddLotDelayedPriceTime = 0;
  /**
   * The extendedPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("extendedPrice")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double extendedPrice = 0.0D;
  /**
   * The extendedChange schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("extendedChange")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double extendedChange = 0.0D;
  /**
   * The extendedChangePercent schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("extendedChangePercent")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double extendedChangePercent = 0.0D;
  /**
   * The extendedPriceTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("extendedPriceTime")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Integer extendedPriceTime = 0;
  /**
   * The previousClose schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("previousClose")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double previousClose = 0.0D;
  /**
   * The previousVolume schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("previousVolume")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Integer previousVolume = 0;
  /**
   * The change schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("change")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double change = 0.0D;
  /**
   * The changePercent schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("changePercent")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double changePercent = 0.0D;
  /**
   * The iexMarketPercent schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexMarketPercent")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double iexMarketPercent = 0.0D;
  /**
   * The iexVolume schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexVolume")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Integer iexVolume = 0;
  /**
   * The avgTotalVolume schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("avgTotalVolume")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Integer avgTotalVolume = 0;
  /**
   * The iexBidPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexBidPrice")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double iexBidPrice = 0.0D;
  /**
   * The iexBidSize schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexBidSize")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Integer iexBidSize = 0;
  /**
   * The iexAskPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexAskPrice")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double iexAskPrice = 0.0D;
  /**
   * The iexAskSize schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexAskSize")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Integer iexAskSize = 0;
  /**
   * The marketCap schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("marketCap")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Integer marketCap = 0;
  /**
   * The week52High schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("week52High")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double week52High = 0.0D;
  /**
   * The week52Low schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("week52Low")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double week52Low = 0.0D;
  /**
   * The ytdChange schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("ytdChange")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double ytdChange = 0.0D;
  /**
   * The peRatio schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("peRatio")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Double peRatio = 0.0D;
  /**
   * The lastTradeTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("lastTradeTime")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Integer lastTradeTime = 0;
  /**
   * The isUSMarketOpen schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("isUSMarketOpen")
  @JsonPropertyDescription("An explanation about the purpose of this instance.")
  private Boolean isUSMarketOpen = false;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  /**
   * The symbol schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("symbol")
  public String getSymbol() {
    return symbol;
  }

  /**
   * The symbol schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("symbol")
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  /**
   * The companyName schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("companyName")
  public String getCompanyName() {
    return companyName;
  }

  /**
   * The companyName schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("companyName")
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  /**
   * The calculationPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("calculationPrice")
  public String getCalculationPrice() {
    return calculationPrice;
  }

  /**
   * The calculationPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("calculationPrice")
  public void setCalculationPrice(String calculationPrice) {
    this.calculationPrice = calculationPrice;
  }

  /**
   * The open schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("open")
  public Integer getOpen() {
    return open;
  }

  /**
   * The open schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("open")
  public void setOpen(Integer open) {
    this.open = open;
  }

  /**
   * The openTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("openTime")
  public Integer getOpenTime() {
    return openTime;
  }

  /**
   * The openTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("openTime")
  public void setOpenTime(Integer openTime) {
    this.openTime = openTime;
  }

  /**
   * The close schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("close")
  public Double getClose() {
    return close;
  }

  /**
   * The close schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("close")
  public void setClose(Double close) {
    this.close = close;
  }

  /**
   * The closeTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("closeTime")
  public Integer getCloseTime() {
    return closeTime;
  }

  /**
   * The closeTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("closeTime")
  public void setCloseTime(Integer closeTime) {
    this.closeTime = closeTime;
  }

  /**
   * The high schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("high")
  public Double getHigh() {
    return high;
  }

  /**
   * The high schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("high")
  public void setHigh(Double high) {
    this.high = high;
  }

  /**
   * The low schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("low")
  public Double getLow() {
    return low;
  }

  /**
   * The low schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("low")
  public void setLow(Double low) {
    this.low = low;
  }

  /**
   * The latestPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("latestPrice")
  public Double getLatestPrice() {
    return latestPrice;
  }

  /**
   * The latestPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("latestPrice")
  public void setLatestPrice(Double latestPrice) {
    this.latestPrice = latestPrice;
  }

  /**
   * The latestSource schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("latestSource")
  public String getLatestSource() {
    return latestSource;
  }

  /**
   * The latestSource schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("latestSource")
  public void setLatestSource(String latestSource) {
    this.latestSource = latestSource;
  }

  /**
   * The latestTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("latestTime")
  public String getLatestTime() {
    return latestTime;
  }

  /**
   * The latestTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("latestTime")
  public void setLatestTime(String latestTime) {
    this.latestTime = latestTime;
  }

  /**
   * The latestUpdate schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("latestUpdate")
  public Integer getLatestUpdate() {
    return latestUpdate;
  }

  /**
   * The latestUpdate schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("latestUpdate")
  public void setLatestUpdate(Integer latestUpdate) {
    this.latestUpdate = latestUpdate;
  }

  /**
   * The latestVolume schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("latestVolume")
  public Integer getLatestVolume() {
    return latestVolume;
  }

  /**
   * The latestVolume schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("latestVolume")
  public void setLatestVolume(Integer latestVolume) {
    this.latestVolume = latestVolume;
  }

  /**
   * The volume schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("volume")
  public Integer getVolume() {
    return volume;
  }

  /**
   * The volume schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("volume")
  public void setVolume(Integer volume) {
    this.volume = volume;
  }

  /**
   * The iexRealtimePrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexRealtimePrice")
  public Double getIexRealtimePrice() {
    return iexRealtimePrice;
  }

  /**
   * The iexRealtimePrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexRealtimePrice")
  public void setIexRealtimePrice(Double iexRealtimePrice) {
    this.iexRealtimePrice = iexRealtimePrice;
  }

  /**
   * The iexRealtimeSize schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexRealtimeSize")
  public Integer getIexRealtimeSize() {
    return iexRealtimeSize;
  }

  /**
   * The iexRealtimeSize schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexRealtimeSize")
  public void setIexRealtimeSize(Integer iexRealtimeSize) {
    this.iexRealtimeSize = iexRealtimeSize;
  }

  /**
   * The iexLastUpdated schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexLastUpdated")
  public Integer getIexLastUpdated() {
    return iexLastUpdated;
  }

  /**
   * The iexLastUpdated schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexLastUpdated")
  public void setIexLastUpdated(Integer iexLastUpdated) {
    this.iexLastUpdated = iexLastUpdated;
  }

  /**
   * The delayedPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("delayedPrice")
  public Double getDelayedPrice() {
    return delayedPrice;
  }

  /**
   * The delayedPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("delayedPrice")
  public void setDelayedPrice(Double delayedPrice) {
    this.delayedPrice = delayedPrice;
  }

  /**
   * The delayedPriceTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("delayedPriceTime")
  public Integer getDelayedPriceTime() {
    return delayedPriceTime;
  }

  /**
   * The delayedPriceTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("delayedPriceTime")
  public void setDelayedPriceTime(Integer delayedPriceTime) {
    this.delayedPriceTime = delayedPriceTime;
  }

  /**
   * The oddLotDelayedPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("oddLotDelayedPrice")
  public Double getOddLotDelayedPrice() {
    return oddLotDelayedPrice;
  }

  /**
   * The oddLotDelayedPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("oddLotDelayedPrice")
  public void setOddLotDelayedPrice(Double oddLotDelayedPrice) {
    this.oddLotDelayedPrice = oddLotDelayedPrice;
  }

  /**
   * The oddLotDelayedPriceTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("oddLotDelayedPriceTime")
  public Integer getOddLotDelayedPriceTime() {
    return oddLotDelayedPriceTime;
  }

  /**
   * The oddLotDelayedPriceTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("oddLotDelayedPriceTime")
  public void setOddLotDelayedPriceTime(Integer oddLotDelayedPriceTime) {
    this.oddLotDelayedPriceTime = oddLotDelayedPriceTime;
  }

  /**
   * The extendedPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("extendedPrice")
  public Double getExtendedPrice() {
    return extendedPrice;
  }

  /**
   * The extendedPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("extendedPrice")
  public void setExtendedPrice(Double extendedPrice) {
    this.extendedPrice = extendedPrice;
  }

  /**
   * The extendedChange schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("extendedChange")
  public Double getExtendedChange() {
    return extendedChange;
  }

  /**
   * The extendedChange schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("extendedChange")
  public void setExtendedChange(Double extendedChange) {
    this.extendedChange = extendedChange;
  }

  /**
   * The extendedChangePercent schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("extendedChangePercent")
  public Double getExtendedChangePercent() {
    return extendedChangePercent;
  }

  /**
   * The extendedChangePercent schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("extendedChangePercent")
  public void setExtendedChangePercent(Double extendedChangePercent) {
    this.extendedChangePercent = extendedChangePercent;
  }

  /**
   * The extendedPriceTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("extendedPriceTime")
  public Integer getExtendedPriceTime() {
    return extendedPriceTime;
  }

  /**
   * The extendedPriceTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("extendedPriceTime")
  public void setExtendedPriceTime(Integer extendedPriceTime) {
    this.extendedPriceTime = extendedPriceTime;
  }

  /**
   * The previousClose schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("previousClose")
  public Double getPreviousClose() {
    return previousClose;
  }

  /**
   * The previousClose schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("previousClose")
  public void setPreviousClose(Double previousClose) {
    this.previousClose = previousClose;
  }

  /**
   * The previousVolume schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("previousVolume")
  public Integer getPreviousVolume() {
    return previousVolume;
  }

  /**
   * The previousVolume schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("previousVolume")
  public void setPreviousVolume(Integer previousVolume) {
    this.previousVolume = previousVolume;
  }

  /**
   * The change schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("change")
  public Double getChange() {
    return change;
  }

  /**
   * The change schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("change")
  public void setChange(Double change) {
    this.change = change;
  }

  /**
   * The changePercent schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("changePercent")
  public Double getChangePercent() {
    return changePercent;
  }

  /**
   * The changePercent schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("changePercent")
  public void setChangePercent(Double changePercent) {
    this.changePercent = changePercent;
  }

  /**
   * The iexMarketPercent schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexMarketPercent")
  public Double getIexMarketPercent() {
    return iexMarketPercent;
  }

  /**
   * The iexMarketPercent schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexMarketPercent")
  public void setIexMarketPercent(Double iexMarketPercent) {
    this.iexMarketPercent = iexMarketPercent;
  }

  /**
   * The iexVolume schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexVolume")
  public Integer getIexVolume() {
    return iexVolume;
  }

  /**
   * The iexVolume schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexVolume")
  public void setIexVolume(Integer iexVolume) {
    this.iexVolume = iexVolume;
  }

  /**
   * The avgTotalVolume schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("avgTotalVolume")
  public Integer getAvgTotalVolume() {
    return avgTotalVolume;
  }

  /**
   * The avgTotalVolume schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("avgTotalVolume")
  public void setAvgTotalVolume(Integer avgTotalVolume) {
    this.avgTotalVolume = avgTotalVolume;
  }

  /**
   * The iexBidPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexBidPrice")
  public Double getIexBidPrice() {
    return iexBidPrice;
  }

  /**
   * The iexBidPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexBidPrice")
  public void setIexBidPrice(Double iexBidPrice) {
    this.iexBidPrice = iexBidPrice;
  }

  /**
   * The iexBidSize schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexBidSize")
  public Integer getIexBidSize() {
    return iexBidSize;
  }

  /**
   * The iexBidSize schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexBidSize")
  public void setIexBidSize(Integer iexBidSize) {
    this.iexBidSize = iexBidSize;
  }

  /**
   * The iexAskPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexAskPrice")
  public Double getIexAskPrice() {
    return iexAskPrice;
  }

  /**
   * The iexAskPrice schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexAskPrice")
  public void setIexAskPrice(Double iexAskPrice) {
    this.iexAskPrice = iexAskPrice;
  }

  /**
   * The iexAskSize schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexAskSize")
  public Integer getIexAskSize() {
    return iexAskSize;
  }

  /**
   * The iexAskSize schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("iexAskSize")
  public void setIexAskSize(Integer iexAskSize) {
    this.iexAskSize = iexAskSize;
  }

  /**
   * The marketCap schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("marketCap")
  public Integer getMarketCap() {
    return marketCap;
  }

  /**
   * The marketCap schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("marketCap")
  public void setMarketCap(Integer marketCap) {
    this.marketCap = marketCap;
  }

  /**
   * The week52High schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("week52High")
  public Double getWeek52High() {
    return week52High;
  }

  /**
   * The week52High schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("week52High")
  public void setWeek52High(Double week52High) {
    this.week52High = week52High;
  }

  /**
   * The week52Low schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("week52Low")
  public Double getWeek52Low() {
    return week52Low;
  }

  /**
   * The week52Low schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("week52Low")
  public void setWeek52Low(Double week52Low) {
    this.week52Low = week52Low;
  }

  /**
   * The ytdChange schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("ytdChange")
  public Double getYtdChange() {
    return ytdChange;
  }

  /**
   * The ytdChange schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("ytdChange")
  public void setYtdChange(Double ytdChange) {
    this.ytdChange = ytdChange;
  }

  /**
   * The peRatio schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("peRatio")
  public Double getPeRatio() {
    return peRatio;
  }

  /**
   * The peRatio schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("peRatio")
  public void setPeRatio(Double peRatio) {
    this.peRatio = peRatio;
  }

  /**
   * The lastTradeTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("lastTradeTime")
  public Integer getLastTradeTime() {
    return lastTradeTime;
  }

  /**
   * The lastTradeTime schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("lastTradeTime")
  public void setLastTradeTime(Integer lastTradeTime) {
    this.lastTradeTime = lastTradeTime;
  }

  /**
   * The isUSMarketOpen schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("isUSMarketOpen")
  public Boolean getIsUSMarketOpen() {
    return isUSMarketOpen;
  }

  /**
   * The isUSMarketOpen schema
   * <p>
   * An explanation about the purpose of this instance.
   * (Required)
   *
   */
  @JsonProperty("isUSMarketOpen")
  public void setIsUSMarketOpen(Boolean isUSMarketOpen) {
    this.isUSMarketOpen = isUSMarketOpen;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}