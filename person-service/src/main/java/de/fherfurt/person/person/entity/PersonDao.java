package de.fherfurt.person.person.entity;

import de.fherfurt.person.core.persistence.JpaGenericDao;
import de.fherfurt.person.person.entity.core.IPersonDao;
import de.fherfurt.person.person.entity.models.Account;
import de.fherfurt.person.person.entity.models.Person;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.Collection;

public class PersonDao extends JpaGenericDao<Person> implements IPersonDao {

    public PersonDao(EntityManager em )
    {
        super( Person.class, em );
    }

    @Override
    public Person findByEmail(String email) {
        return (Person) getEntityManager()
                .createQuery("SELECT e FROM " + getEntityClass().getCanonicalName() + " e JOIN e.account a where a.email = :EMAIL")
                .setParameter("EMAIL", email)
                .getSingleResult();

        /*CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = cb.createQuery( Person.class );

        Metamodel meta = this.entityManager.getMetamodel();
        EntityType<Person> personMeta = meta.entity( Person.class );

        Root<Person> person = criteriaQuery.from( Person.class );
        Join<Person, Account> account = person.join( Person_.account );

        criteriaQuery.select(person).where(cb.equal(account.get("email"), email));

        return this.entityManager.createQuery( criteriaQuery ).getSingleResult();*/
    }

    @Override
    public Collection<Person> findByName(String query) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = cb.createQuery(Person.class);
        Root<Person> from = criteriaQuery.from(Person.class);

        Predicate firstnameCheck = cb.like(from.get("firstname"), "%" + query + "%");
        Predicate lastnameCheck = cb.like(from.get("lastname"), "%" + query + "%");

        criteriaQuery.select(from).where(cb.or(firstnameCheck, lastnameCheck));

        return this.entityManager.createQuery( criteriaQuery ).getResultList();
    }

    @Override
    public Collection<Person> findByFaculty(int facultyId) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = cb.createQuery(Person.class);

        Root<Person> from = criteriaQuery.from(Person.class);
        criteriaQuery.select(from).where(cb.equal(from.get("facultyId"), facultyId));

        return this.entityManager.createQuery( criteriaQuery ).getResultList();
    }

}
