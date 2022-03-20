package de.fherfurt.person.person.business;

import de.fherfurt.person.person.entity.Person;
import de.fherfurt.person.person.entity.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * This business facade is used to work (means save, find and delete)
 * {@link Person}s.
 *
 * @author Jonas Liehmann <jonas.liehmann@fh-erfurt.de>
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class PersonBF {

    private final PersonRepository personRepository = PersonRepository.of();

    public Optional<Person> findBy(final int id) {
        return personRepository.findBy(id);
    }

    public void delete(final int id) {
        final Optional<Person> toDelete = findBy(id);

        if (toDelete.isEmpty())
            return;

        personRepository.delete(toDelete.get());
    }

}
