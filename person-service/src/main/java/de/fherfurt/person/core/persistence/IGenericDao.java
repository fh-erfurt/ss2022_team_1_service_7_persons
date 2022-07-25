package de.fherfurt.person.core.persistence;

import java.util.Collection;

public interface IGenericDao<T extends BaseBusinessEntity>
{
    /**
     * Find all persisted entities.
     *
     * @return All persisted entities or an empty collection
     */
    Collection<T> findAll();

    /**
     * Searches for an entity by its unique id.
     *
     * @param id The id of the searched entity
     * @return The found entity or null
     */
    T findById( Long id );

    /**
     * Creates a new entity to the underlying storage.
     *
     * @param entity Entity to persist to the database
     */
    boolean create( T entity );

    /**
     * Updates an entity in the underlying storage.
     *
     * @param entity Entity to update in the database
     */
    T update( T entity );

    /**
     * Deletes an entity by its unique id.
     *
     * @param id ID of the entity to delete
     */
    boolean delete( Long id );
}
