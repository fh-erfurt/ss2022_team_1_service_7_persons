import java.util.Arrays;

class PersonNotFoundException extends Exception
{
    public PersonNotFoundException (String str) { super(str); }
}

public class SearchingModule {

    public static Person[] getPersonsByFacultyID(Person[] persons, int id) {
        return Arrays.stream(persons).filter(person -> person.getFaculty().getId() == id).toArray(Person[]::new);
    }

    public static Person[] getPersonsByName(Person[] persons, String name) {
        return null;
    }

    public static Person getPersonByID(Person[] persons, int id) throws PersonNotFoundException {
        Person[] results = Arrays.stream(persons).filter(person -> person.getId() == id).toArray(Person[]::new);
        if (results.length > 0) return results[0];
        else throw new PersonNotFoundException("Person with id " + id + " was not found.");
    }

}
