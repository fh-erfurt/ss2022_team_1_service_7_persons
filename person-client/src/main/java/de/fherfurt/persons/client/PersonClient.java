package de.fherfurt.persons.client;

import de.fherfurt.persons.client.objects.AccountDto;
import de.fherfurt.persons.client.objects.ImageDto;
import de.fherfurt.persons.client.objects.PersonDto;

import java.util.List;
import java.util.Optional;

/**
 * This interface defines the provided functionality for persons in the
 * <i>Persons-Service</i>. It should be used by other services and contains
 * the connection related settings in the future.
 *
 * @author Niklas Schumann <niklas.schumann@fh-erfurt.de>
 * @author Justin Noske <justin.noske@fh-erfurt.de>
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
public interface PersonClient
{
    /**
     * Takes the id of a {@link PersonDto} and searches for its user account. If the corresponding {@link AccountDto}
     * is found, it will be returned, otherwise an empty {@link Optional} is returned.
     *
     * @param id The ID of the person to find the account for.
     * @return The found account with matching user id.
     */
    Optional<AccountDto> findAccountById( int id );

    /**
     * Takes the id of a {@link PersonDto} and loads the related {@link ImageDto} which represents the persons
     * profile image. This {@link ImageDto} contains the image as byte array. If no related {@link ImageDto} was found,
     * an empty {@link Optional} is returned.
     *
     * @param id The ID of the person to find the account for.
     * @return The found account with matching user id.
     */
    Optional<ImageDto> loadPersonsImage( int id );

    /**
     * Takes the id of a {@link PersonDto} and searches it. If the corresponding {@link PersonDto}
     * is found, it will be returned, otherwise an empty {@link Optional} is returned.
     *
     * @param id The ID of the person.
     * @return The found Person or an empty optional.
     */
    PersonDto findById( int id );

    /**
     * Takes the email of a {@link PersonDto} and searches it. If the corresponding {@link PersonDto}
     * is found, it will be returned, otherwise an empty {@link Optional} is returned.
     *
     * @param email The email of the person.
     * @return The found Person or an empty optional.
     */
    PersonDto findByEmail( String email );

    /**
     * Takes the faculty of a {@link PersonDto} and searches all persons in the
     * faculty. If corresponding {@link PersonDto} are found, it will return them
     * as a {@link List}. If no persons match the faculty (e.g. wrong faculty name),
     * an empty {@link List} is returned.
     *
     * @param facultyId The faculty id of the person.
     * @return The found persons as list.
     */
    List<PersonDto> findByFaculty( int facultyId );

    /**
     * Takes the name of a {@link PersonDto} and searches all persons somehow
     * matching the provided name. If corresponding {@link PersonDto} are found, it
     * will return them as a {@link List}. If no persons match the provided name phrase,
     * an empty {@link List} is returned.
     *
     * @param name The name (phrase) of the person.
     * @return The found persons as list.
     */
    List<PersonDto> findByName( String name );

    /**
     * Takes the semester of a {@link PersonDto} and searches all persons holding
     * lectures in this semester. If no persons match semester (e.g. wrong input: -1),
     * an empty {@link List} is returned.
     *
     * @param semester The semester to search for matching lecturers.
     * @return The found persons as list.
     */
    List<PersonDto> findBySemester( int semester );

    /**
     * Takes the ID of a {@link PersonDto} and deletes it.
     *
     * @param id The ID of the person that must be deleted
     */
    void deleteBy( int id );

    /**
     * Takes a person instance of a {@link PersonDto} object and
     * saves it to the database.
     *
     * @param person The person which should get saved.
     */
    void save( PersonDto person );
}