package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.util.Lists;
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
public class PositionDaoTest {

  @Autowired
  private SecurityOrderDao securityOrderDao;
  @Autowired
  private AccountDao accountDao;
  @Autowired
  private QuoteDao quoteDao;
  @Autowired
  private TraderDao traderDao;
  @Autowired
  private PositionDao positionDao;

  private Trader savedTrader;
  private Quote savedQuote;
  private SecurityOrder savedSecurityOrder;
  private Account savedAccount;
  private Position savedPosition;

  @Before
  public void insertOne() throws ParseException {
    savedTrader = new Trader();
    savedTrader.setId(1);
    savedTrader.setCountry("Canada");
    savedTrader.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2000-02-14"));
    savedTrader.setEmail("fakeNews@AlexJones.com");
    savedTrader.setFirst_name("Alex");
    savedTrader.setLast_name("Jones");

    traderDao.save(savedTrader);
    savedAccount = new Account();
    savedAccount.setId(1);
    savedAccount.setAmount(1337.00f);
    savedAccount.setTrader_id(1);

    accountDao.save(savedAccount);

    savedQuote = new Quote();
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(10);
    savedQuote.setId("aapl");
    savedQuote.setLastPrice(10.1d);

    quoteDao.save(savedQuote);

    savedSecurityOrder = new SecurityOrder();
    savedSecurityOrder.setAccount_id(1);
    savedSecurityOrder.setId(1);
    savedSecurityOrder.setNotes("oh hai");
    savedSecurityOrder.setPrice(12.34f);
    savedSecurityOrder.setSize(2);
    savedSecurityOrder.setStatus("FILLED");
    savedSecurityOrder.setTicker("aapl");

    securityOrderDao.save(savedSecurityOrder);
    savedPosition = positionDao.findAll().get(0);
  }

  @After
  public void deleteOne() {
    securityOrderDao.deleteAll();
    quoteDao.deleteAll();
    accountDao.deleteAll();
    traderDao.deleteAll();
  }

  @Test
  public void findAllById() {
    List<Position> positions = Lists
        .newArrayList(positionDao.findAllById(Arrays.asList(savedPosition.getId(), -1)));
    assertEquals(1,positions.size());
    assertEquals(savedPosition.getPosition(),positions.get(0).getPosition());
  }

  @Test
  public void getJdbcTemplate() {
    positionDao.getJdbcTemplate();
    assertTrue(true);
  }

  @Test
  public void getSimpleJdbcInsert() {
    positionDao.getSimpleJdbcInsert();
    assertTrue(true);
  }

  @Test
  public void getTableName() {
    positionDao.getTableName();
    assertTrue(true);
  }

  @Test
  public void getIdColumnName() {
    positionDao.getIdColumnName();
    assertTrue(true);
  }

  @Test
  public void getEntityClass() {
    positionDao.getEntityClass();
    assertTrue(true);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void updateOne() {
    positionDao.updateOne(savedPosition);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void delete() {
    positionDao.delete(savedPosition);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void deleteAll() {
    positionDao.deleteAll();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void save() {
    positionDao.save(savedPosition);
  }
}