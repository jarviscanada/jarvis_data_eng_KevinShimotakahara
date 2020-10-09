package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
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
public class QuoteDaoIntTest {

  @Autowired
  private QuoteDao quoteDao;

  private Quote savedQuote;
  private Quote savedQuote2;
  private Quote savedQuote3;

  @Before
  public void insertOne() {
    savedQuote = new Quote();
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(10);
    savedQuote.setId("aapl");
    savedQuote.setLastPrice(10.1d);

    savedQuote2 = new Quote();
    savedQuote2.setAskPrice(10d);
    savedQuote2.setAskSize(10);
    savedQuote2.setBidPrice(10.2d);
    savedQuote2.setBidSize(10);
    savedQuote2.setId("googl");
    savedQuote2.setLastPrice(10.1d);

    savedQuote3 = new Quote();
    savedQuote3.setAskPrice(10d);
    savedQuote3.setAskSize(10);
    savedQuote3.setBidPrice(10.2d);
    savedQuote3.setBidSize(10);
    savedQuote3.setId("fb");
    savedQuote3.setLastPrice(10.1d);

    quoteDao.save(savedQuote);
  }

  @Test
  public void quoteDaoIntTest() throws Exception {
    List<Quote> quotes = new LinkedList<>();
    quotes.add(savedQuote);
    quotes.add(savedQuote2);
    quotes.add(savedQuote3);

    assertEquals(quotes,quoteDao.saveAll(quotes));
    assertEquals(savedQuote.getTicker(),quoteDao.findById("aapl").get().getTicker());
    List<Quote> expected = quoteDao.findAll();
    assertEquals(quotes.get(0).getTicker(), expected.get(0).getTicker());
    assertEquals(quotes.get(1).getTicker(), expected.get(1).getTicker());
    assertEquals(quotes.get(2).getTicker(), expected.get(2).getTicker());
    assertEquals(3,quoteDao.count());
  }

  @After
  public void deleteOne() {
    quoteDao.deleteById(savedQuote.getId());
    quoteDao.deleteAll();
  }
}
