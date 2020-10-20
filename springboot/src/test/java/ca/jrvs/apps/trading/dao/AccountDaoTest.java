package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
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
public class AccountDaoTest {

  @Autowired
  private AccountDao accountDao;

  @Autowired
  private TraderDao traderDao;

  private Trader savedTrader;
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
  }

  @After
  public void deleteOne() {
    accountDao.deleteAll();
    traderDao.deleteById(savedTrader.getId());
    traderDao.deleteAll();
  }

  @Test
  public void findAllById() {
    List<Account> accounts = Lists
        .newArrayList(accountDao.findAllById(Arrays.asList(savedAccount.getId(), -1)));
    assertEquals(1,accounts.size());
    assertEquals(savedAccount.getAmount(),accounts.get(0).getAmount());
  }

  @Test
  public void getJdbcTemplate() {
    accountDao.getJdbcTemplate();
    assertTrue(true);
  }

  @Test
  public void getSimpleJdbcInsert() {
    accountDao.getSimpleJdbcInsert();
    assertTrue(true);
  }

  @Test
  public void getTableName() {
    accountDao.getTableName();
    assertTrue(true);
  }

  @Test
  public void getIdColumnName() {
    accountDao.getIdColumnName();
    assertTrue(true);
  }

  @Test
  public void getEntityClass() {
    accountDao.getEntityClass();
    assertTrue(true);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void updateOne() {
    accountDao.updateOne(savedAccount);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void delete() {
    accountDao.delete(savedAccount);
  }

  @Test
  public void deleteAll() {
    accountDao.deleteAll();
    assertEquals(0,accountDao.count());
    accountDao.save(savedAccount);
  }
}