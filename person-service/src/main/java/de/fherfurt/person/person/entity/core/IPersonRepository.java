package de.fherfurt.person.person.entity.core;
import de.fherfurt.person.person.entity.models.Person;

import java.util.List;

/**
 * @author Jonas Liehmann <jonas.liehmann@fh-erfurt.de>
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
public interface IPersonRepository {

    /**
     * Find all persisted entities.
     *
     * @return All persisted entities or an empty list
     */
    List<Person> findAll();

    /**
     * Find an entity by its id.
     *
     * @param id The id of the searched entity
     * @return The found entity
     */
    Person findBy( final long id );

    /**
     * Find an entity by its email.
     *
     * @param email Email of the person
     * @return The found entity
     */
    Person findByEmail( final String email );


    /**
     * Find entities by its name.
     *
     * @param name The name (phrase) of the persons firstname or lastname or both.
     * @return The entities or an empty list
     */
    List<Person> findByName( final String name );

    /**
     * Find entities by its faculty id.
     *
     * @param id The faculty id of the person.
     * @return The entities or an empty list
     */
    List<Person> findByFaculty( final int id );

    /**
     * Save an entity to the underlying storage. It doesn't matter, if the entity is new or already saved.
     * In case of update the changes are written too.
     *
     * @param person Instance to persist to database
     */
    boolean save( final Person person );

    /**
     * Deletes a given entity.
     *
     * @param id Id of the person to delete
     */
    boolean delete( final long id );
}