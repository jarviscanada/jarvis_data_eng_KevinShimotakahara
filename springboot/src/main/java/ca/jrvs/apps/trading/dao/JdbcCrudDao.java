package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public abstract class JdbcCrudDao<T extends Entity<Integer>> implements CrudRepository<T, Integer> {

  private Class<T> clazz;

  private static final Logger logger = LoggerFactory.getLogger(JdbcCrudDao.class);

  abstract public JdbcTemplate getJdbcTemplate();

  abstract public SimpleJdbcInsert getSimpleJdbcInsert();

  abstract public String getTableName();

  abstract public String getIdColumnName();

  abstract Class<T> getEntityClass();

  /**
   * Save an entity and update auto--generated integer ID
   * @param entity to be saved
   * @return save entity
   */
  @Override
  public <S extends T> S save(S entity) {
    if (existsById(entity.getId())) {
      if(updateOne(entity) != 1) {
        throw new DataRetrievalFailureException("Unable to update record");
      }
    } else {
      addOne(entity);
    }
    return entity;
  }

  @Override
  public <S extends  T> List<S> saveAll(Iterable<S> entities) {
    Iterator<S> it = entities.iterator();
    List<S> quoteList = new LinkedList<>();
    while(it.hasNext()){
      quoteList.add(save(it.next()));
    }
    return quoteList;
  }

  /**
   * helper method that saves one quote
   */
  private <S extends T> void addOne(S entity) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(entity);

    Number newId = getSimpleJdbcInsert().executeAndReturnKey(parameterSource);
    entity.setId(newId.intValue());
  }

  /**
   * helper method that updates one quote
   */
  abstract public int updateOne(T entity);

  @Override
  public Optional<T> findById(Integer id) {
    Optional<T> entity = Optional.empty();
    String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + "=?";

    try {
      entity = Optional.ofNullable((T) getJdbcTemplate()
        .queryForObject(selectSql, BeanPropertyRowMapper.newInstance(getEntityClass()), id));
    } catch (IncorrectResultSizeDataAccessException e) {
      logger.debug("Can't find trader id:" + id, e);
    }
    return entity;
  }

  @Override
  public boolean existsById(Integer id) {
    int rowCount = getJdbcTemplate().queryForObject("select count(*) from " + getTableName() + " WHERE " + getIdColumnName() + "=?", Integer.class,id);
    if(rowCount == 0)
      return false;
    return true;
  }

  @Override
  public List<T> findAll() {
    //find multiple rows (findByColumnName)
    String selectSql = "SELECT * FROM " + getTableName();
    List<T> things =  getJdbcTemplate()
        .query(selectSql, BeanPropertyRowMapper.newInstance(clazz));
    return things;
  }

  @Override
  public List<T> findAllById(Iterable<Integer> ids) {
    StringBuilder inClause = new StringBuilder("(");
    Iterator<Integer> it = ids.iterator();
    while(it.hasNext()){
      Integer id = it.next();
      if (this.existsById(id))
        inClause.append(id + ",");
    }

    if (inClause.length() == 1){
      return new LinkedList<T>();
    } else{
      inClause.setCharAt(inClause.length()-1,')');
      String in = inClause.toString();
      String selectSql = "SELECT * FROM " + getTableName() + " WHERE "+ getIdColumnName() + " IN " + in;
      List<T> things =  getJdbcTemplate()
          .query(selectSql, BeanPropertyRowMapper.newInstance(clazz));
      return things;
    }
  }

  @Override
  public void deleteById(Integer id){
    if (id == null) {
      throw new IllegalArgumentException("ID can't be null");
    }
    String deleteSql = "DELETE FROM " + getTableName() + " WHERE "+ getIdColumnName() + "=?";
    getJdbcTemplate().update(deleteSql, id);
  }

  @Override
  public long count() {
    return getJdbcTemplate().queryForObject("select count(*) from " + getTableName(), Long.class);
  }

  @Override
  public void deleteAll() {
    String deleteSql = "DELETE FROM " + getTableName();
    getJdbcTemplate().update(deleteSql);
  }

  public Class<T> getClazz() {
    return clazz;
  }

  public void setClazz(Class<T> clazz) {
    this.clazz = clazz;
  }
}
