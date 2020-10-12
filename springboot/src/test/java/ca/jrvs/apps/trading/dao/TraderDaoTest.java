package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
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
public class TraderDaoTest {

  @Autowired
  private TraderDao traderDao;

  private Trader savedTrader;

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
  }

  @After
  public void deleteOne() {
    traderDao.deleteById(savedTrader.getId());
    traderDao.deleteAll();
  }

  @Test
  public void findAllById() {
    List<Trader> traders = Lists
        .newArrayList(traderDao.findAllById(Arrays.asList(savedTrader.getId(), -1)));
    assertEquals(1,traders.size());
    assertEquals(savedTrader.getCountry(),traders.get(0).getCountry());
  }

  @Test
  public void getJdbcTemplate() {
    traderDao.getJdbcTemplate();
    assertTrue(true);
  }

  @Test
  public void getSimpleJdbcInsert() {
    traderDao.getSimpleJdbcInsert();
    assertTrue(true);
  }

  @Test
  public void getTableName() {
    traderDao.getTableName();
    assertTrue(true);
  }

  @Test
  public void getIdColumnName() {
    traderDao.getIdColumnName();
    assertTrue(true);
  }

  @Test
  public void getEntityClass() {
    traderDao.getEntityClass();
    assertTrue(true);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void updateOne() {
    traderDao.updateOne(savedTrader);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void delete() {
    traderDao.delete(savedTrader);
  }

  @Test
  public void deleteAll() {
    traderDao.deleteAll();
    assertEquals(0,traderDao.count());
    traderDao.save(savedTrader);
  }
}