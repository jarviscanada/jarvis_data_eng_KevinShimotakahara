package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteDao implements CrudRepository<Quote,String> {
  private static final String TABLE_NAME = "quote";
  private static final String ID_COLUMN_NAME = "ticker";

  private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public QuoteDao(DataSource dataSource){
    jdbcTemplate = new JdbcTemplate(dataSource);
    simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
  }

  @Override
  public Quote save(Quote quote) {
    if (existsById(quote.getTicker())) {
      int updatedRowNo = updateOne(quote);
      if (updatedRowNo != 1) {
        throw new DataRetrievalFailureException("Unable to update quote");
      }
    } else {
      addOne(quote);
    }
    return quote;
  }

  /**
   * helper method that saves one quote
   */
  private void addOne(Quote quote){
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
    int row = simpleJdbcInsert.execute(parameterSource);
    if(row != 1) {
      throw new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);
    }
  }

  private int updateOne(Quote quote) {
    String update_sql = "UPDATE quote SET last_price=?, bid_price=?,"
                        +"bid_size=?, ask_price=?, ask_size=? WHERE ticker=?";
    return jdbcTemplate.update(update_sql, makeUpdateValues(quote));
  }

  /**
   * helper method that makes sql update values objects
   * @param quote to be updated
   * @return UPDATE_SQL values
   */
  private Object[] makeUpdateValues(Quote quote){
    Object[] fields = {quote.getLastPrice(),
                       quote.getBidPrice(),
                       quote.getBidSize(),
                       quote.getAskPrice(),
                       quote.getAskSize(),
                       quote.getTicker()};
    return fields;
  }

  @Override
  public <S extends  Quote> List<S> saveAll(Iterable<S> quotes) {
    Iterator<S> it = quotes.iterator();
    List<S> quoteList = new LinkedList<>();
    while(it.hasNext()){
      quoteList.add((S) save(it.next()));
    }
    return quoteList;
  }

  /**
   * Find a quote by ticker
   * @param ticker name
   * @return quote or Optional.empty if not found
   */
  @Override
  public Optional<Quote> findById(String ticker) {
    Quote quote = jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " WHERE ticker=?", BeanPropertyRowMapper.newInstance(Quote.class),ticker);
    if (quote == null)
      return Optional.empty();
    return Optional.of(quote);
  }

  @Override
  public boolean existsById(String ticker) {
    int rowCount = jdbcTemplate.queryForObject("select count(*) from " + TABLE_NAME + " WHERE ticker=?", Integer.class,ticker);
    if(rowCount == 0)
      return false;
    return true;
  }

  @Override
  public List<Quote> findAll() {
    //find multiple rows (findByColumnName)
    String selectSql = "SELECT * FROM " + TABLE_NAME;
    List<Quote> quotes =  jdbcTemplate
        .query(selectSql, BeanPropertyRowMapper.newInstance(Quote.class));
    return quotes;
  }

  @Override
  public long count() {
    return jdbcTemplate.queryForObject("select count(*) from " + TABLE_NAME, Long.class);
  }

  @Override
  public void deleteById(String ticker) {
    if (ticker == null) {
      throw new IllegalArgumentException("ID can't be null");
    }
    String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE ticker=?";
    jdbcTemplate.update(deleteSql, ticker);
  }

  @Override
  public void deleteAll() {
    String deleteSql = "DELETE FROM " + TABLE_NAME;
    jdbcTemplate.update(deleteSql);
  }

  @Override
  public Iterable<Quote> findAllById(Iterable<String> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void delete(Quote quote) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends Quote> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
