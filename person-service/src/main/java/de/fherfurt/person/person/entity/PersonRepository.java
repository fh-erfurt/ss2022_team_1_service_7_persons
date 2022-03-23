package de.fherfurt.person.person.entity;

import de.fherfurt.person.core.persistence.Database;
import de.fherfurt.person.core.persistence.Repository;
import de.fherfurt.person.core.persistence.errors.NoResultException;
import de.fherfurt.person.core.persistence.errors.ToManyResultsException;

import de.fherfurt.persons.client.objects.PersonDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author Jonas Liehmann <jonas.liehmann@fh-erfurt.de>
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonRepository implements Repository<Person> {

    private final Database database = Database.newInstance();

    public static PersonRepository of() {
        return new PersonRepository();
    }

    /**
     * Save an entity to the underlying storage. It doesn't matter, if the entity is
     * new or already saved.
     * In case of update the changes are written too.
     *
     * @param entity Instance to save
     */
    @Override
    public void save(final Person entity) {
        database.save(entity);
    }

    /**
     * Find an entity by its id. If no entity is available, an empty
     * {@link Optional} is returned.
     *
     * @param id Id of the searched entity
     * @return The entity or empty
     */
    @Override
    public Optional<Person> findBy(int id) {
        return database.findBy(Person.class, id);
    }

    /**
     * Find an entity by its email. If no entity is available, an empty
     * {@link Optional} is returned.
     *
     * @throws NoResultException      If no user with the mail was found.
     * @throws ToManyResultsException If multiple users was found in the database
     *                                with the same id.
     *
     * @param email Email of the searched entity
     * @return The entity or empty
     */
    public Optional<Person> findByEmail(String email) {
        List<Person> persons = database.findBy(Person.class, person -> Objects.equals(person.getEmail(), email));

        if (persons.size() > 1) {
            throw new ToManyResultsException("No unique result found for email [" + email + "]");
        }

        if (persons.size() == 0) return Optional.empty();

        return Optional.of(persons.get(0));
    }

    /**
     * Find entities by its name.
     *
     * @param name The name (phrase) of the person.
     * @return The entities or an empty list
     */
    public List<Person> findByName(String name) {
        // Create pattern to search for phrase with any characters before or after
        Pattern namePattern = Pattern.compile("^.*" + name + ".*$");

        // Test pattern for every person and collect if match
        return database.findBy(Person.class, person -> namePattern.matcher(person.getFirstname() + " " + person.getLastname()).find());
    }

    /**
     * Find entities by its faculty id.
     *
     * @param facultyId The faculty id of the person.
     * @return The entities or an empty list
     */
    public List<Person> findByFaculty(int facultyId) {
        // Test pattern for every person and collect if match
        return database.findBy(Person.class, person -> Objects.equals(person.getFacultyId(), facultyId));
    }

    /**
     * Deletes a given entity.
     *
     * @param entity Instance to delete
     */
    @Override
    public void delete(Person entity) {
        database.delete(entity);
    }
}