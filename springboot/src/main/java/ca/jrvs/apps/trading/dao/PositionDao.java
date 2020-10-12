package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Entity;
import ca.jrvs.apps.trading.model.domain.Position;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class PositionDao extends JdbcCrudDao<Position> {
  private final String TABLE_NAME = "security_order";
  private final String ID_COLUMN = "account_id";

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleInsert;

  @Autowired
  public PositionDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME)
        .usingGeneratedKeyColumns(ID_COLUMN);
    super.setClazz(Position.class);
  }

  @Override
  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  @Override
  public SimpleJdbcInsert getSimpleJdbcInsert() {
    return simpleInsert;
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  @Override
  public String getIdColumnName() {
    return ID_COLUMN;
  }

  @Override
  Class<Position> getEntityClass() {
    return Position.class;
  }

  /**
   * helper method that updates one quote
   *
   * @param entity
   */
  @Override
  public int updateOne(Position entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void delete(Position position) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends Position> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * As the position table is read only since it is actually a view, it does not make sense
   * to meddle with its "contents", as they are based off other tables. As such, we must override
   * delete and save methods to throw UnsupportedOperationExceptions.
   */
  @Override
  public void deleteAll(){
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteById(Integer id){
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public <S extends Position> List<S> saveAll(Iterable<S> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public Position save(Position position){
    throw new UnsupportedOperationException("Not implemented");
  }
}
