package de.fherfurt.person.person.business;

import de.fherfurt.person.person.entity.Person;
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

    public void save(final Person person) {
        personRepository.save(person);
    }

    public Optional<Person> findBy(final int id) {
        return personRepository.findBy(id);
    }

    public Optional<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public List<Person> findByName(String name) {
        return personRepository.findByName(name);
    }

    public List<Person> findByFaculty(int facultyId) {
        return personRepository.findByFaculty(facultyId);
    }

    public void delete(final int id) {
        final Optional<Person> toDelete = findBy(id);

        if (toDelete.isEmpty())
            return;

        personRepository.delete(toDelete.get());
    }

}
