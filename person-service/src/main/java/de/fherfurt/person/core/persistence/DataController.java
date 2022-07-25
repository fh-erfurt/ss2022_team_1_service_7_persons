package de.fherfurt.person.core.persistence;

import de.fherfurt.person.person.entity.PersonRepository;
import de.fherfurt.person.person.entity.core.IPersonDao;
import de.fherfurt.person.person.entity.core.IPersonRepository;
import de.fherfurt.person.person.entity.models.Account;
import de.fherfurt.person.person.entity.PersonDao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataController
{
    private static final String PERSISTENCE_UNIT_NAME = "persons-unit";

    private final EntityManagerFactory entityManagerFactory;

    private static DataController instance;

    public static DataController getInstance()
    {
        if( instance == null )
            instance = new DataController();

        return instance;
    }

    private DataController()
    {
        this.entityManagerFactory = Persistence.createEntityManagerFactory( PERSISTENCE_UNIT_NAME );
    }

    public IPersonRepository getPersonRepository() {
        return new PersonRepository( getPersonDao() );
    }

    public IPersonDao getPersonDao()
    {
        return new PersonDao( this.entityManagerFactory.createEntityManager() );
    }

    public IGenericDao<Account> getAccountDao()
    {
        return new JpaGenericDao<>( Account.class, this.entityManagerFactory.createEntityManager() );
    }

}

