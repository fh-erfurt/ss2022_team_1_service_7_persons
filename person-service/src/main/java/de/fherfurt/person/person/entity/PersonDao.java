package de.fherfurt.person.person.entity;

import de.fherfurt.person.core.persistence.GenericDao;
import de.fherfurt.person.person.entity.core.IPersonDao;
import de.fherfurt.person.person.entity.models.Person;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.Collection;

/**
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
public class PersonDao extends GenericDao<Person> implements IPersonDao {

    public PersonDao( EntityManager em )
    {
        super( Person.class, em );
    }

    /**
     * Find a person by its email.
     *
     * @param email Email of the person
     * @return The found person
     */
    @Override
    public Person findByEmail( String email ) {
        return ( Person ) getEntityManager()
                .createQuery( "SELECT e FROM " + getEntityClass().getCanonicalName() + " e JOIN e.account a where a.email = :EMAIL" )
                .setParameter( "EMAIL", email )
                .getSingleResult();
    }

    /**
     * Find persons by their name. You can provide a Full name, a part of it or whatever you want. The database
     * gets fetched with pattern matching.
     *
     * @param query The persons firstname or lastname or both.
     * @return The persons or an empty list.
     */
    @Override
    public Collection<Person> findByName( String query ) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = cb.createQuery( Person.class );
        Root<Person> from = criteriaQuery.from( Person.class );

        Predicate firstnameCheck = cb.like( from.get( "firstname" ), "%" + query + "%" );
        Predicate lastnameCheck = cb.like( from.get( "lastname" ), "%" + query + "%" );

        criteriaQuery.select( from ).where( cb.or( firstnameCheck, lastnameCheck ) );

        return this.entityManager.createQuery( criteriaQuery ).getResultList();
    }

    /**
     * Find persons by its faculty id.
     *
     * @param id The faculty id of the person.
     * @return The persons of the faculty or an empty list if faculty doesn't exist
     */
    @Override
    public Collection<Person> findByFaculty( int id ) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = cb.createQuery( Person.class );

        Root<Person> from = criteriaQuery.from( Person.class );
        criteriaQuery.select( from ).where( cb.equal( from.get( "facultyId" ), id ) );

        return this.entityManager.createQuery( criteriaQuery ).getResultList();
    }

}
