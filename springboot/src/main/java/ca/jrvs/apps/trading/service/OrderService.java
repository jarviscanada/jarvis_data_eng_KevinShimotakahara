package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.MarketOrderDto;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderService {

  private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

  private AccountDao accountDao;
  private SecurityOrderDao securityOrderDao;
  private QuoteDao quoteDao;
  private PositionDao positionDao;

  @Autowired
  public OrderService(AccountDao accountDao, SecurityOrderDao securityOrderDao,
      QuoteDao quoteDao, PositionDao positionDao) {
    this.accountDao = accountDao;
    this.securityOrderDao = securityOrderDao;
    this.quoteDao = quoteDao;
    this.positionDao = positionDao;
  }

  /**
   * Execute a market order
   *
   * - validate the order (e.g. size, and ticker)
   * - Create a securityOrder (for security_order table)
   * - Handle buy or sell order
   *  - buy order : check account balance (calls helper method)
   *  - sell order : check position for the ticker/symbol (calls helper method)
   *  - (please don't forget to update securityOrder.status)
   * - Save and return securityOrder
   *
   * NOTE: you will need some helper methods (protected or private)
   *
   * @param orderDto market order
   * @return SecurityOrder from security_order table
   * @throws org.springframework.dao.DataAccessException if unable to get data from DAO
   * @throws IllegalArgumentException for invalid input
   */
  public SecurityOrder executeMarketOrder(MarketOrderDto orderDto){
    //validate order
    String ticker = orderDto.getTicker();
    Integer size = orderDto.getSize();
    Integer id = orderDto.getId();
    if(size > 0 && quoteDao.existsById(ticker)){
      Double price = null;
      try {
        price = quoteDao.findById(ticker).get().getLastPrice();
      } catch(Exception e){
        throw new InvalidDataAccessResourceUsageException("Failed to access quote information.");
      }
      if(orderDto.getType().toUpperCase().equals("BUY")){
        if(checkAccountBalance(id) >= price*size){
          //make security order
          SecurityOrder securityOrder = new SecurityOrder();
          securityOrder.setTicker(ticker);
          securityOrder.setStatus("FILLED");
          securityOrder.setId(id);
          securityOrder.setAccount_id(id);
          securityOrder.setSize(size);
          securityOrder.setPrice(price.floatValue());
          securityOrder.setNotes("Buy request");
          securityOrderDao.save(securityOrder);
          updateAccountBalance(id,-price.floatValue()*size.floatValue());
          return securityOrder;
        }
      } else {
        if(checkPosition(id,ticker) >= size){
          //make security order
          SecurityOrder securityOrder = new SecurityOrder();
          securityOrder.setTicker(ticker);
          securityOrder.setStatus("FILLED");
          securityOrder.setId(id);
          securityOrder.setAccount_id(id);
          securityOrder.setSize(-size);
          securityOrder.setPrice(price.floatValue());
          securityOrder.setNotes("Sell request");
          securityOrderDao.save(securityOrder);
          updateAccountBalance(id,price.floatValue()*size.floatValue());
          return securityOrder;
        }
      }
    }
    throw new IllegalArgumentException("Invalid input.");
  }

  private void updateAccountBalance(Integer id, Float change){
    Account acc = accountDao.findById(id).get();
    acc.setAmount(change + checkAccountBalance(id));
    accountDao.updateAmountById(acc);
  }

  private Float checkAccountBalance(Integer id){
    return accountDao.findById(id).get().getAmount();
  }

  private Integer checkPosition(Integer id, String ticker){
    return positionDao.findAllById(Arrays.asList(id)).stream()
        .filter(position -> position.getTicker().equals(ticker))
        .collect(Collectors.toList()).get(0).getPosition();
  }
}
