package de.fherfurt.person.core.persistence;

import java.util.Collection;
import java.util.List;

public interface IGenericDao<T extends BaseBusinessEntity>
{
    T findById( Long id );
    Collection<T> findAll();

    boolean create( T entity );
    boolean createAll( Collection<T> newEntities );

    T update( T entity );

    boolean delete( Long id );
    boolean delete( T entity );
    boolean delete( List<T> entries );
}
