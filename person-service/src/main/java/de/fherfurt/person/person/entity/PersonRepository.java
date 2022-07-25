package de.fherfurt.person.person.entity;

import de.fherfurt.person.person.entity.core.IPersonDao;
import de.fherfurt.person.person.entity.core.IPersonRepository;
import de.fherfurt.person.person.entity.models.Image;
import de.fherfurt.person.person.entity.models.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository implements IPersonRepository {

    private final IPersonDao personDao;

    public PersonRepository( IPersonDao personDao ) {
        this.personDao = personDao;
    }


    /**
     * Find all persisted entities.
     *
     * @return All persisted entities or an empty list
     */
    @Override
    public List<Person> findAll() {
        return new ArrayList<>( this.personDao.findAll() );
    }

    /**
     * Find an entity by its id.
     *
     * @param id The id of the searched entity
     * @return The found entity
     */
    @Override
    public Person findBy( final long id ) {
        return this.personDao.findById( id );
    }

    /**
     * Find an entity by its email.
     *
     * @param email Email of the person
     * @return The found entity
     */
    @Override
    public Person findByEmail( final String email ) {
        return this.personDao.findByEmail( email );
    }

    /**
     * Find entities by its name.
     *
     * @param name The name (phrase) of the persons firstname or lastname or both.
     * @return The entities or an empty list
     */
    @Override
    public List<Person> findByName( final String name ) {
        return new ArrayList<>( this.personDao.findByName( name ) );
    }

    /**
     * Find entities by its faculty id.
     *
     * @param id The faculty id of the person.
     * @return The entities or an empty list
     */
    @Override
    public List<Person> findByFaculty( final int id ) {
        return new ArrayList<>( this.personDao.findByFaculty( id ) );
    }

    /**
     * Save an entity to the underlying storage. It doesn't matter, if the entity is new or already saved.
     * In case of update the changes are written too.
     *
     * @param person Instance to persist to database
     */
    @Override
    public boolean save( final Person person ) {
        if ( person.getId() != 0 ) {
            return personDao.update( person ) != null;
        }

        return personDao.create( person );
    }

    /**
     * Deletes a given entity.
     *
     * @param id ID of the person to delete
     */
    @Override
    public boolean delete( final long id ) {
        return this.personDao.delete( id );
    }
}
