package de.fherfurt.person.person.business;

import de.fherfurt.person.person.entity.models.Person;
import de.fherfurt.person.person.entity.PersonRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * This business facade is used to work (means save, find and delete)
 * {@link Person}s.
 *
 * @author Jonas Liehmann <jonas.liehmann@fh-erfurt.de>
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@RequiredArgsConstructor(staticName = "of")
public class PersonBF {

    private final PersonRepository personRepository = PersonRepository.of();

    /**
     * Save a person to the underlying storage.
     *
     * @param person Instance to save
     */
    public void save(final Person person) {
        personRepository.save(person);
    }

    /**
     * Find an person by its id. If no entity is available, an empty
     * {@link Optional} is returned.
     *
     * @param id Id of the searched entity
     * @return The person or empty
     */
    public Optional<Person> findBy(final int id) {
        return personRepository.findBy(id);
    }

    /**
     * Find a person by its email. If no person is available, an empty {@link Optional} is returned.
     *
     * @param email Email of the searched entity
     * @return The person or empty
     */
    public Optional<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    /**
     * Find persons by its name. If no person is available, an empty {@link List} is returned.
     *
     * @param name The name (phrase) of the person.
     * @return The persons or empty list
     */
    public List<Person> findByName(String name) {
        return personRepository.findByName(name);
    }

    /**
     * Find persons by its faculty id. If no person is available, an empty {@link List} is returned.
     *
     * @param facultyId The faculty id of the persons
     * @return The persons or empty list
     */
    public List<Person> findByFaculty(int facultyId) {
        return personRepository.findByFaculty(facultyId);
    }

    /**
     * Deletes a person by its id.
     *
     * @param id Id of the person to delete.
     */
    public void delete(final int id) {
        final Optional<Person> toDelete = findBy(id);

        if (toDelete.isEmpty())
            return;

        personRepository.delete(toDelete.get());
    }

}
