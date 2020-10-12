package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
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
public class SecurityOrderDaoTest {

  @Autowired
  private SecurityOrderDao securityOrderDao;
  @Autowired
  private AccountDao accountDao;
  @Autowired
  private QuoteDao quoteDao;
  @Autowired
  private TraderDao traderDao;

  private Trader savedTrader;
  private Quote savedQuote;
  private SecurityOrder savedSecurityOrder;
  private Account savedAccount;

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
    savedSecurityOrder.setStatus("idklol");
    savedSecurityOrder.setTicker("aapl");

    securityOrderDao.save(savedSecurityOrder);
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
    List<SecurityOrder> securityOrders = Lists
        .newArrayList(securityOrderDao.findAllById(Arrays.asList(savedSecurityOrder.getId(), -1)));
    assertEquals(1,securityOrders.size());
    assertEquals(savedSecurityOrder.getStatus(),securityOrders.get(0).getStatus());
  }

  @Test
  public void getJdbcTemplate() {
    securityOrderDao.getJdbcTemplate();
    assertTrue(true);
  }

  @Test
  public void getSimpleJdbcInsert() {
    securityOrderDao.getSimpleJdbcInsert();
    assertTrue(true);
  }

  @Test
  public void getTableName() {
    securityOrderDao.getTableName();
    assertTrue(true);
  }

  @Test
  public void getIdColumnName() {
    securityOrderDao.getIdColumnName();
    assertTrue(true);
  }

  @Test
  public void getEntityClass() {
    securityOrderDao.getEntityClass();
    assertTrue(true);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void updateOne() {
    securityOrderDao.updateOne(savedSecurityOrder);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void delete() {
    securityOrderDao.delete(savedSecurityOrder);
  }

  @Test
  public void deleteAll() {
    securityOrderDao.deleteAll();
    assertEquals(0,securityOrderDao.count());
    securityOrderDao.save(savedSecurityOrder);
  }
}