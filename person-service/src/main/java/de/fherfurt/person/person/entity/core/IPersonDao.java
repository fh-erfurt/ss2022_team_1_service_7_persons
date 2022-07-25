package de.fherfurt.person.person.entity.core;

import de.fherfurt.person.core.persistence.IGenericDao;
import de.fherfurt.person.person.entity.models.Person;

import java.util.Collection;

/**
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
public interface IPersonDao extends IGenericDao<Person> {

    /**
     * Find a person by its email.
     *
     * @param email Email of the person
     * @return The found person
     */
    Person findByEmail( String email );

    /**
     * Find persons by their name. You can provide a Full name, a part of it or whatever you want. The database
     * gets fetched with pattern matching.
     *
     * @param query The persons firstname or lastname or both.
     * @return The persons or an empty list.
     */
    Collection<Person> findByName( String query );

    /**
     * Find persons by its faculty id.
     *
     * @param id The faculty id of the person.
     * @return The persons of the faculty or an empty list if faculty doesn't exist
     */
    Collection<Person> findByFaculty( int facultyId );
}
