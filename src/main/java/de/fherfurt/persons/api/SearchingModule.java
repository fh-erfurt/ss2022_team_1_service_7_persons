package de.fherfurt.persons.api;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import de.fherfurt.persons.api.errors.PersonNotFoundException;
import de.fherfurt.persons.models.Person;

/**
 * Module to search in a list of persons for various tokens.
 */
public class SearchingModule {
    /**
     * Returns all persons found with the corresponding Faculty id.
     *
     * @param persons The reference to an array list of persons.
     * @param faculty The name of the faculty.
     *
     * @return An list of found persons. If none were found, it an empty list.
     */
    public static List<Person> getPersonsByFaculty(List<Person> persons, String faculty) {
        return persons.stream()
                .filter(person -> Objects.equals(person.getFaculty(), faculty))
                .collect(Collectors.toList());
    }

    /**
     * Returns all persons found with a similar name to the provided phrase.
     *
     * @param persons The reference to an array of persons.
     * @param phrase  The phrase to search person names.
     *
     * @return An list of found persons. If none were found, it returns an empty
     *         list.
     */
    public static List<Person> getPersonsByName(List<Person> persons, String phrase) {
        // Create pattern to search for phrase with any characters before or after
        Pattern namePattern = Pattern.compile("^.*" + phrase + ".*$");

        // Test pattern for every person and collect if match
        return persons.stream()
                .filter(person -> namePattern.matcher(person.getName()).find())
                .collect(Collectors.toList());
    }

    /**
     * Returns the Person with the provided id.
     *
     * @param persons The reference to an array of persons.
     * @param id      The id of the searched person.
     *
     * @throws PersonNotFoundException - Throws this Exception, if no user with this
     *                                 id exists.
     *
     * @return The found Person.
     */
    public static Person getPersonByID(List<Person> persons, int id) throws PersonNotFoundException {
        List<Person> results = persons.stream()
                .filter(person -> person.getId() == id)
                .collect(Collectors.toList());

        if (results.size() > 0) {
            return results.get(0);
        } else {
            throw new PersonNotFoundException("Person with id " + id + " was not found.");
        }
    }
}
