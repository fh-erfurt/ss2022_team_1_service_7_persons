import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/** Class representing an Exception thrown if no person was found. */
class PersonNotFoundException extends Exception
{
    /**
     * Creates an instance of PersonNotFoundException.
     * @constructor
     *
     * @param msg {String} - The error message which should get thrown.
     */
    public PersonNotFoundException (String msg) { super(msg); }
}

/** Class with static methods to search for persons. */
public class SearchingModule {

    /**
     * Returns all persons found with the corresponding Faculty id.
     *
     * @param persons {Person[]} - The reference to an array of persons.
     * @param id {int}  - The id of the searched person.
     *
     * @return {Person[]} - An array of found persons. If none were found, it returns [].
     */
    public static Person[] getPersonsByFacultyID(Person[] persons, int id) {
        return Arrays.stream(persons).filter(person -> person.getFaculty().getId() == id).toArray(Person[]::new);
    }

    /**
     * Returns all persons found with a similar name to the provided phrase.
     *
     * @param persons {Person[]} - The reference to an array of persons.
     * @param phrase {String}  - The phrase to search person names.
     *
     * @return {Person[]} - An array of found persons. If none were found, it returns [].
     */
    public static Person[] getPersonsByName(Person[] persons, String phrase) {
        ArrayList<Person> foundPersons = new ArrayList<>();

        // Create pattern to search for phrase with any characters before or after
        Pattern namePattern = Pattern.compile("^.*" + phrase + ".*$");

        // Test pattern for every person and safe if match
        for (Person person: persons) {
            if (namePattern.matcher(person.getName()).find()) {
                foundPersons.add(person);
            }
        }

        return foundPersons.toArray(Person[]::new);
    }

    /**
     * Returns the Person with the provided id.
     *
     * @param persons {Person[]} - The reference to an array of persons.
     * @param id {int}  - The id of the searched person.
     *
     * @throws PersonNotFoundException - Throws this Exception, if no user with this id exists.
     *
     * @return {Person[]} - An array of found persons.
     */
    public static Person getPersonByID(Person[] persons, int id) throws PersonNotFoundException {
        Person[] results = Arrays.stream(persons).filter(person -> person.getId() == id).toArray(Person[]::new);
        if (results.length > 0) return results[0];
        else throw new PersonNotFoundException("Person with id " + id + " was not found.");
    }

}
