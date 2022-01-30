package de.fherfurt.persons.modules;

import org.json.JSONObject;

import de.fherfurt.persons.core.classes.CheckedBuilder;
import de.fherfurt.persons.models.Person;

/**
 * Builder class to create person instances. This class is also able
 * to validate the person.
 * 
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
public class PersonBuilder extends CheckedBuilder<PersonBuilder, Person> {
    private int id = -1;
    private int faculty = -1;
    private String firstname = "";
    private String lastname = "";

    /**
     * Creates the person from json schema.
     *
     * @param json The json schema to get the data from.
     * 
     * @return The current builder instance.
     */
    public PersonBuilder fromJSON(String json) {
        JSONObject obj = new JSONObject(json);

        firstname = obj.getString("firstname");
        lastname = obj.getString("lastname");
        faculty = obj.getInt("faculty");

        return this;
    }

    /**
     * Creates the person from person object to update its values.
     *
     * @param person The json schema to get the data from.
     * 
     * @return The current builder instance.
     */
    public PersonBuilder fromPerson(Person person) {
        id = person.getId();
        firstname = person.getFirstname();
        lastname = person.getLastname();

        return this;
    }

    /**
     * Adds the name to the person.
     *
     * @param firstname The firstname of the person.
     * @param lastname  The lastname of the person.
     * 
     * @return The current builder instance.
     */
    public PersonBuilder withName(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        return this;
    }

    /**
     * Adds the faculty id to the person.
     *
     * @param faculty The faculty id of the person.
     * 
     * @return The current builder instance.
     */
    public PersonBuilder withFaculty(int faculty) {
        this.faculty = faculty;
        return this;
    }

    /**
     * Creates a new person object from attributes.
     *
     * @return The person object with defined values.
     */
    @Override
    protected Person instantiate() {
        // If id isnt set, create person without id to let
        // it set the autoincrement id.
        if (id == -1) {
            return new Person(faculty, firstname, lastname);
        }
        // Else its an update operation, keep the id of the
        // person.
        else {
            return new Person(id, faculty, firstname, lastname);
        }
    }
}
