package de.fherfurt.person.person.entity.core;
import de.fherfurt.person.person.entity.models.Person;

import java.util.List;

/**
 * @author Jonas Liehmann <jonas.liehmann@fh-erfurt.de>
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
public interface IPersonRepository {

    /**
     * Find all persisted persons.
     *
     * @return All persisted persons or an empty list
     */
    List<Person> findAll();

    /**
     * Searches for a person by its unique id.
     *
     * @param id The id of the searched person
     * @return The found person or null
     */
    Person findBy( final long id );

    /**
     * Find a person by its email.
     *
     * @param email Email of the person
     * @return The found person
     */
    Person findByEmail( final String email );


    /**
     * Find persons by their name. You can provide a Full name, a part of it or whatever you want. The database
     * gets fetched with pattern matching.
     *
     * @param name The persons firstname or lastname or both.
     * @return The persons or an empty list.
     */
    List<Person> findByName( final String name );

    /**
     * Find persons by its faculty id.
     *
     * @param id The faculty id of the person.
     * @return The persons of the faculty or an empty list if faculty doesn't exist
     */
    List<Person> findByFaculty( final int id );

    /**
     * Save an entity to the underlying storage. It doesn't matter, if the entity is new or already saved.
     * In case of update the changes are written too.
     *
     * @param person Person to persist / update to the database
     */
    boolean save( final Person person );

    /**
     * Deletes a person by its unique id.
     *
     * @param id ID of the person to delete
     */
    boolean delete( final long id );
}