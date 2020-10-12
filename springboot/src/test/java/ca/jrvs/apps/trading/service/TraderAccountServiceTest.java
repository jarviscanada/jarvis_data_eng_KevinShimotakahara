package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class TraderAccountServiceTest {

  private TraderAccountView savedView;
  @Autowired
  private TraderAccountService traderAccountService;
  @Autowired
  private TraderDao traderDao;
  @Autowired
  private AccountDao accountDao;

  @Test
  public void createTraderAndAccount() throws ParseException {
    Trader trader = new Trader();
    trader.setCountry("Canada");
    trader.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2000-02-14"));
    trader.setEmail("fakeNews@AlexJones.com");
    trader.setFirst_name("Alex");
    trader.setLast_name("Jones");
    TraderAccountView tav = traderAccountService.createTraderAndAccount(trader);
    assertTrue(tav.getAccount().getAmount() == 0f && tav.getTrader().getFirst_name() == "Alex");
  }

  @Test
  public void deleteTraderById() throws ParseException {
    Trader trader = new Trader();
    trader.setCountry("Canada");
    trader.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2000-02-14"));
    trader.setEmail("fakeNews@AlexJones.com");
    trader.setFirst_name("Alex");
    trader.setLast_name("Jones");
    TraderAccountView tav = traderAccountService.createTraderAndAccount(trader);

    traderAccountService.deleteTraderById(tav.getTrader().getId());
    assertTrue(traderDao.count() == 0);
  }

  @Test
  public void withdraw() throws ParseException {
    Trader trader = new Trader();
    trader.setCountry("Canada");
    trader.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2000-02-14"));
    trader.setEmail("fakeNews@AlexJones.com");
    trader.setFirst_name("Alex");
    trader.setLast_name("Jones");
    TraderAccountView tav = traderAccountService.createTraderAndAccount(trader);
    Account account = tav.getAccount();
    account.setAmount(500f);
    accountDao.updateAmountById(account);
    assert(traderAccountService.withdraw(tav.getTrader().getId(),300d).getAmount() == 200f);
  }
}