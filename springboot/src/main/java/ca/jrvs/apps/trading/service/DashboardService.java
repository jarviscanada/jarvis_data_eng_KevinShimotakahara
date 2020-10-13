package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.PortfolioView;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import java.util.Arrays;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DashboardService {

  private TraderDao traderDao;
  private PositionDao positionDao;
  private AccountDao accountDao;
  private QuoteDao quoteDao;

  @Autowired
  public DashboardService(TraderDao traderDao, PositionDao positionDao, AccountDao accountDao,
      QuoteDao quoteDao) {
    this.traderDao = traderDao;
    this.positionDao = positionDao;
    this.accountDao = accountDao;
    this.quoteDao = quoteDao;
  }

  /**
   * Create and return a traderAccountView by trader ID
   * - get trader account by id
   * - get trader info by id
   * - create and return a traderAccountView
   *
   * @param traderId must not be null
   * @return traderAccountView
   * @throws IllegalArgumentException if traderId is null or not found
   */
  public TraderAccountView getTraderAccount(Integer traderId) {
    if(traderId == null)
      throw new IllegalArgumentException("traderId passed is null");
    if(traderDao.findById(traderId).isPresent())
      return new TraderAccountView(traderDao.findById(traderId).get(),accountDao.findById(traderId).get());
    throw new IllegalArgumentException("traderId not found.");
  }

  /**
   * Create and return a portfolioView by trader ID
   * - get account by trader id
   * - get positions by account id
   * - create and return a portfolioView
   *
   * @prarm traderId must not be null
   * @return portfolioView
   * @throws IllegalArgumentException if traderId is null or not found
   */
  public PortfolioView getProfileViewByTraderId(Integer traderId) {
    if(traderId == null)
      throw new IllegalArgumentException("traderId passed is null");
    if(accountDao.findById(traderId).isPresent() && positionDao.findAllById(Arrays.asList(traderId)).size() > 0)
      return new PortfolioView(accountDao.findById(traderId).get(),positionDao.findAllById(Arrays.asList(traderId)));
    throw new IllegalArgumentException("traderId not found or trader has no portfolio yet.");
  }

}
