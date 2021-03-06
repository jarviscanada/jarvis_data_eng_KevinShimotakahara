package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.util.FourOFourNotFoundException;

public interface CrdDao<T, ID> {

  /**
   * Create an entity(Tweet) to the underlying storage
   * @param entity entity that to be created
   * @return created entity
   */
  T create(T entity) throws FourOFourNotFoundException;

  /**
   * Find an entity(Tweet) by its id
   * @param id entity id
   * @return Tweet entity
   */
  T findById(ID id) throws FourOFourNotFoundException;

  /**
   * Delete an entity(Tweet) by its ID
   * @param id of the entity to be deleted
   * @return deleted entity
   */
  T deleteById(ID id) throws FourOFourNotFoundException;
}