package de.fherfurt.person.core.persistence;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * Base definition for every dao which implements basic functionality.
 *
 * @author Tobias Kärst <tobias.kärst@fh-erfurt.de>
 */
public class GenericDao<T extends BaseBusinessEntity> implements IGenericDao<T>
{
    protected final Class<T> persistentClass;
    protected final EntityManager entityManager;

    public GenericDao(Class<T> type, EntityManager entityManager ) {
        this.persistentClass = type;
        this.entityManager = entityManager;
    }

    public Class<T> getEntityClass()
    {
        return persistentClass;
    }

    public EntityManager getEntityManager()
    {
        return entityManager;
    }

    /**
     * Searches for an entity by its unique id.
     *
     * @param id The id of the searched entity
     * @return The found entity or null
     */
    @Override
    public T findById( final Long id ) {
        return getEntityManager().find( persistentClass, id );
    }

    /**
     * Find all persisted entities.
     *
     * @return All persisted entities or an empty collection
     */
    @Override
    @SuppressWarnings("unchecked")
    public Collection<T> findAll( String sortBy, String orderBy ) {
        sortBy = sortBy.equals("") ? "id" : sortBy;
        orderBy = orderBy.equals("") ? "ASC" : orderBy;

        return (Collection<T>) getEntityManager()
                .createQuery("SELECT e FROM " + getEntityClass().getCanonicalName() + " e ORDER BY e." + sortBy + " " + orderBy )
                .getResultList();
    }

    /**
     * Creates a new entity to the underlying storage.
     *
     * @param entity Entity to persist to the database
     */
    @Override
    public boolean create( T entity ) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist( entity );
        getEntityManager().getTransaction().commit();

        return true;
    }

    /**
     * Updates an entity in the underlying storage.
     *
     * @param entity Entity to update in the database
     */
    @Override
    public T update( T entity ) {
        getEntityManager().getTransaction().begin();
        final T savedEntity = getEntityManager().merge( entity );
        getEntityManager().getTransaction().commit();

        return savedEntity;
    }

    /**
     * Deletes an entity by its unique id.
     *
     * @param id ID of the entity to delete
     */
    @Override
    public boolean delete( Long id ) {
        T entity = this.findById( id );
        return this.delete( entity );
    }

    private boolean delete( T entity ) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove( entity );
        getEntityManager().getTransaction().commit();

        return true;
    }
}