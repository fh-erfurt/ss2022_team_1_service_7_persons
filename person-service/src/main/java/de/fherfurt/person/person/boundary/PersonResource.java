package de.fherfurt.person.person.boundary;

import de.fherfurt.person.person.business.PersonBF;
import de.fherfurt.persons.client.PersonClient;
import de.fherfurt.persons.client.objects.PersonDto;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * This class represents the production implementation for the functionality of
 * the person submodule.
 *
 * @author Jonas Liehmann <jonas.liehmann@fh-erfurt.de>
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@NoArgsConstructor(staticName = "of")
public class PersonResource implements PersonClient {
    private final PersonBF ratingBF = PersonBF.of();

    /**
     * Takes the id of a {@link PersonDto} and searches it. If the corresponding
     * {@link PersonDto} is found,
     * it will be returned, otherwise an empty {@link Optional} is returned.
     *
     * @param id The ID of the person.
     * @return The found Person or an empty optional.
     */
    @Override
    public Optional<PersonDto> findById(int id) {
        return Optional.empty();
    }

    /**
     * Takes the email of a {@link PersonDto} and searches it. If the corresponding
     * {@link PersonDto} is found,
     * it will be returned, otherwise an empty {@link Optional} is returned.
     *
     * @param email The email of the person.
     * @return The found Person or an empty optional.
     */
    @Override
    public Optional<PersonDto> findByEmail(String email) {
        return Optional.empty();
    }

    /**
     * Takes the faculty of a {@link PersonDto} and searches all persons in the
     * faculty. If corresponding
     * {@link PersonDto} are found, it will return them as a {@link List}. If no
     * persons match the faculty (e.g. wrong
     * faculty name), an empty {@link List} is returned.
     *
     * @param facultyId The faculty id of the person.
     * @return The found persons as list.
     */
    @Override
    public List<PersonDto> findByFaculty(int facultyId) {
        return null;
    }

    /**
     * Takes the name of a {@link PersonDto} and searches all persons somehow
     * matching the provided name. If
     * corresponding {@link PersonDto} are found, it will return them as a
     * {@link List}. If no persons match
     * the provided name phrase, an empty {@link List} is returned.
     *
     * @param name The name (phrase) of the person.
     * @return The found persons as list.
     */
    @Override
    public List<PersonDto> findByName(String name) {
        return null;
    }

    /**
     * Takes the ID of a {@link PersonDto} and deletes it.
     *
     * @param id The ID of the person that must be deleted
     */
    @Override
    public void deleteBy(int id) {

    }
}
