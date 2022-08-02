package de.fherfurt.person.person.entity;

import de.fherfurt.person.person.entity.core.IPersonDao;
import de.fherfurt.person.person.entity.core.IPersonRepository;
import de.fherfurt.person.person.entity.models.Person;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonas Liehmann <jonas.liehmann@fh-erfurt.de>
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonRepository implements IPersonRepository {

    public static PersonRepository of() {
        return new PersonRepository();
    }

    private IPersonDao personDao;

    public PersonRepository(IPersonDao personDao) {
        this.personDao = personDao;
    }

    /**
     * Find all persisted persons.
     *
     * @return All persisted persons or an empty list
     */
    @Override
    public List<Person> findAll( String sortBy, String orderBy ) {
        return new ArrayList<>( this.personDao.findAll( sortBy, orderBy ) );
    }

    /**
     * Searches for a person by its unique id.
     *
     * @param id The id of the searched person
     * @return The found person or null
     */
    @Override
    public Person findBy( final long id ) {
        return this.personDao.findById( id );
    }

    /**
     * Find a person by its email.
     *
     * @param email Email of the person
     * @return The found person
     */
    @Override
    public Person findByEmail( final String email ) {
        return this.personDao.findByEmail( email );
    }

    /**
     * Find persons by their name. You can provide a Full name, a part of it or whatever you want. The database
     * gets fetched with pattern matching.
     *
     * @param name The persons firstname or lastname or both.
     * @return The persons or an empty list.
     */
    @Override
    public List<Person> findByName( final String name ) {
        return new ArrayList<>( this.personDao.findByName( name ) );
    }

    /**
     * Find persons by its faculty id.
     *
     * @param id The faculty id of the person.
     * @return The persons of the faculty or an empty list if faculty doesn't exist
     */
    @Override
    public List<Person> findByFaculty( final int id ) {
        return new ArrayList<>( this.personDao.findByFaculty( id ) );
    }

    /**
     * Save an entity to the underlying storage. It doesn't matter, if the entity is new or already saved.
     * In case of update the changes are written too.
     *
     * @param person Person to persist / update to the database
     */
    @Override
    public boolean save( final Person person ) {
        if ( person.getId() != null && findBy( person.getId() ) != null ) {
            return personDao.update( person ) != null;
        }

        return personDao.create( person );
    }

    /**
     * Deletes a person by its unique id.
     *
     * @param id ID of the person to delete
     */
    @Override
    public boolean delete( final long id ) {
        return this.personDao.delete( id );
    }
}
