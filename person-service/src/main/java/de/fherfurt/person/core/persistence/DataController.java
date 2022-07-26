package de.fherfurt.person.core.persistence;

import de.fherfurt.person.person.entity.PersonRepository;
import de.fherfurt.person.person.entity.core.IPersonDao;
import de.fherfurt.person.person.entity.models.Account;
import de.fherfurt.person.person.entity.PersonDao;
import lombok.Getter;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Controller singleton to access all repositories and dao.
 *
 * @author Tobias Kärst <tobias.kärst@fh-erfurt.de>
 */
public class DataController
{
    private static final String PERSISTENCE_UNIT_NAME = "persons-unit";
    private static DataController instance;

    private final EntityManagerFactory entityManagerFactory;

    private final PersonRepository personRepository;

    public static DataController getInstance() {
        if( instance == null ) instance = new DataController();
        return instance;
    }

    public static PersonRepository getPersonRepository() {
        return getInstance().personRepository;
    }

    private DataController() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory( PERSISTENCE_UNIT_NAME );
        this.personRepository = new PersonRepository( getPersonDao() );
    }

    public IPersonDao getPersonDao() {
        return new PersonDao( this.entityManagerFactory.createEntityManager() );
    }

    public IGenericDao<Account> getAccountDao() {
        return new GenericDao<>( Account.class, this.entityManagerFactory.createEntityManager() );
    }

}

