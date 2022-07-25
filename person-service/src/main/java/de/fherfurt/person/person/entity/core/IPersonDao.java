package de.fherfurt.person.person.entity.core;

import de.fherfurt.person.core.persistence.IGenericDao;
import de.fherfurt.person.person.entity.models.Person;

import java.util.Collection;

/**
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
public interface IPersonDao extends IGenericDao<Person> {
    Person findByEmail( String address );

    Collection<Person> findByName( String name );

    Collection<Person> findByFaculty( int facultyId );
}
