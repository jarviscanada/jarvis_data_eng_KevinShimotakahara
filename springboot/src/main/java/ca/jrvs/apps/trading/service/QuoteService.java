package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.util.ResponseExceptionUtil;
import java.nio.channels.IllegalChannelGroupException;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class QuoteService {

  private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

  private QuoteDao quoteDao;
  private MarketDataDao marketDataDao;

  @Autowired
  public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao) {
    this.quoteDao = quoteDao;
    this.marketDataDao = marketDataDao;
  }

  /**
   * Update quote table against IEX source
   * - get all quotes from the db
   * - foreach ticker get iexQuote
   * - convert iexQuote to quote entity
   * - persist quote to db
   *
   * @throws org.springframework.web.server.ResponseStatusException if ticker is not found from IEX
   * @throws org.springframework.dao.DataAccessException if unable to retrieve data
   * @throws IllegalArgumentException for invalid input
   */
  public void updateMarketData(){
    List<Quote> quotes = quoteDao.findAll();
    try {
      quotes.stream().map(quote -> quote.getTicker())
          .map(ticker -> marketDataDao.findById(ticker).get())
          .map(iexQuote -> buildQuoteFromIexQuote(iexQuote))
          .forEach(quote -> quoteDao.save(quote));
    } catch(IllegalArgumentException e){
      throw ResponseExceptionUtil.getResponseStatusException(e);
    } catch (DataRetrievalFailureException e){
      throw new InvalidDataAccessResourceUsageException("Unable to retrieve data");
    } catch (Exception e){
      throw new IllegalArgumentException("Invalid input?",e);
    }
  }

  /**
   * Helper method. Map an IexQuote to a Quote entity.
   * Note: `iexQuote.getLatestPrice() == null` if the stock market is closed.
   * Make sure to set a default value for number field(s).
   */
  public static Quote buildQuoteFromIexQuote(IexQuote iexQuote){
    Quote quote = new Quote();
    quote.setLastPrice(iexQuote.getLatestPrice() == null ? iexQuote.getLatestPrice() : -1);
    quote.setBidSize(iexQuote.getIexBidSize().intValue());
    quote.setBidPrice(iexQuote.getIexBidPrice());
    quote.setAskSize(iexQuote.getIexAskSize().intValue());
    quote.setAskPrice(iexQuote.getIexAskPrice());
    quote.setTicker(iexQuote.getSymbol().toLowerCase());

    return quote;
  }

  /**
   * Validate (againstIEX) and save given tickers to quote table
   *
   * - Get iexQuote(s)
   * - convert each iexQuote to Quote entity
   * - persist the quote to db
   *
   * @param tickers a list of tickers/symbols
   * @throws IllegalArgumentException if ticker is not found from IEX
   */
  public List<Quote> saveQuotes(List<String> tickers){
    return tickers.stream().map(ticker -> saveQuote(ticker)).collect(Collectors.toList());
  }

  /**
   * Helper method
   */
  public Quote saveQuote(String ticker){
    return quoteDao.save(buildQuoteFromIexQuote(findIexQuoteByTicker(ticker)));
  }

  /**
   * Find an IexQuote
   *
   * @param ticker id
   * @return IexQuote object
   * @throws IllegalArgumentException if ticker is invalid
   */
  public IexQuote findIexQuoteByTicker(String ticker){
    return marketDataDao.findById(ticker)
                        .orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid"));

  }

  /**
   * Update a given quote to quote table without validation
   * @param quote entity
   */
  public Quote saveQuote(Quote quote) { return quoteDao.save(quote); }

  /**
   * Find all quotes from the quote table
   * @return a list of quotes
   */
  public List<Quote> findAllQuotes() { return quoteDao.findAll(); }
}
