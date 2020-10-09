package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteServiceTest extends TestCase {

  @Autowired
  private QuoteService quoteService;

  @Autowired
  private QuoteDao quoteDao;

  @Before
  public void setup() {
    quoteDao.deleteAll();
    List<String> tickers = new LinkedList<>();
    tickers.add("googl");
    tickers.add("aapl");
    tickers.add("fb");
    tickers.add("amd");
    quoteService.saveQuotes(tickers);
  }

  @Test
  public void testSaveQuote() {
    quoteService.saveQuote("amd");
    assertTrue(quoteDao.existsById("amd"));
  }

  @Test
  public void testSaveQuotes() {
    List<String> tickers = new LinkedList<>();
    tickers.add("googl");
    tickers.add("aapl");
    tickers.add("fb");

    quoteService.saveQuotes(tickers);
    assertTrue(quoteDao.existsById("googl"));
    assertTrue(quoteDao.existsById("aapl"));
    assertTrue(quoteDao.existsById("fb"));
  }

  @Test
  public void testUpdateMarketData() {
    //Meddle with fields in quotes table, then see if updateMarketData fixes them
    List<Quote> quotes = quoteService.findAllQuotes();
    quotes.stream().forEach(quote -> {
          quote.setLastPrice(-100.30);
          quoteDao.save(quote);
        });
    quoteService.updateMarketData();
    assertTrue(quoteDao.findById("googl").get().getLastPrice() != -100.30);
    assertTrue(quoteDao.findById("aapl").get().getLastPrice() != -100.30);
    assertTrue(quoteDao.findById("fb").get().getLastPrice() != -100.30);
    assertTrue(quoteDao.findById("amd").get().getLastPrice() != -100.30);
  }

  @Test
  public void testFindIexQuoteByTicker() {
    IexQuote iexQuote = quoteService.findIexQuoteByTicker("amd");
    assertEquals("AMD",iexQuote.getSymbol());
  }

  @Test
  public void testFindAllQuotes() {
    List<Quote> quotes = quoteService.findAllQuotes();
    List<String> tickers = quotes.stream().map(quote -> quote.getTicker()).collect(Collectors.toList());
    assertTrue(tickers.contains("googl") && tickers.contains("aapl")
        && tickers.contains("fb") && tickers.contains("amd"));
  }
}