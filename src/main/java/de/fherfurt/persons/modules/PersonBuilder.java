package de.fherfurt.persons.modules;

import de.fherfurt.persons.core.interfaces.Room;
import de.fherfurt.persons.types.Salutation;
import org.json.JSONObject;

import de.fherfurt.persons.core.classes.CheckedBuilder;
import de.fherfurt.persons.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder class to create person instances. This class is also able
 * to validate the person.
 * 
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
public class PersonBuilder extends CheckedBuilder<PersonBuilder, Person> {
    private int id = -1;
    private String faculty = "";
    private String firstname = "";
    private String lastname = "";
    private String phone;
    private List<String> positions;
    private List<String> titles;
    private String fax;
    private String email;
    private Room room;
    private Salutation salutation;

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
        faculty = obj.getString("faculty");
        phone = obj.getString("phone");
        fax = obj.getString("fax");
        email = obj.getString("email");

        /**
         * TODO: get list values from JSON String
         */
        // positions = (List<String>)obj.getJSONArray("positions").toList();
        // titles = obj.getString("titles");
        // salutation = obj.getString("salutation");
        // room = obj.getEnum("room");

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
        faculty = person.getFaculty();
        positions = person.getPositions();
        phone = person.getPhone();
        titles = person.getTitles();
        fax = person.getFax();
        email = person.getEmail();
        room = person.getRoom();
        salutation = person.getSalutation();

        return this;
    }

    /**
     * Sets the first to the person.
     *
     * @param firstname The firstname of the person.
     *
     * @return The current builder instance.
     */
    public PersonBuilder withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    /**
     * Sets the lastname to the person.
     *
     * @param lastname  The lastname of the person.
     *
     * @return The current builder instance.
     */
    public PersonBuilder withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }



    /**
     * Sets the name to the person.
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
     * Sets the faculty to the person.
     *
     * @param faculty The faculty id of the person.
     * 
     * @return The current builder instance.
     */
    public PersonBuilder withFaculty(String faculty) {
        this.faculty = faculty;
        return this;
    }

    /**
     * Sets the positions to the person.
     *
     * @param positions The positions of the person.
     *
     * @return The current builder instance.
     */
    public PersonBuilder withPositions(List<String> positions) {
        this.positions = positions;
        return this;
    }
    /**
     * Adds the position to the person.
     *
     * @param position {String} A new position for the person.
     *
     * @return The current builder instance.
     */
    public PersonBuilder addPosition(String position) {
        this.positions.add(position);
        return this;
    }


    /**
     * Sets the titles to the person.
     *
     * @param titles The positions of the person.
     *
     * @return The current builder instance.
     */
    public PersonBuilder withTitle(List<String> titles) {
        this.titles = titles;
        return this;
    }
    /**
     * Adds the title to the person.
     *
     * @param title {String} A new title for the person.
     *
     * @return The current builder instance.
     */
    public PersonBuilder addTitle(String title) {
        this.titles.add(title);
        return this;
    }


    /**
     * Sets the room to the person.
     *
     * @param room {Room} The room of the person.
     *
     * @return The current builder instance.
     */
    public PersonBuilder withRoom(Room room) {
        this.room = room;
        return this;
    }

    /**
     * Sets the fax to the person.
     *
     * @param fax {String} The fax of the person.
     *
     * @return The current builder instance.
     */
    public PersonBuilder withFax(String fax) {
        this.fax = fax;
        return this;
    }


    /**
     * Sets the email to the person.
     *
     * @param email {String} The email of the person.
     *
     * @return The current builder instance.
     */
    public PersonBuilder withEmail(String email) {
        this.email = email;
        return this;
    }


    /**
     * Sets the salutation to the person.
     *
     * @param salutation {Salutation} The salutation of the person.
     *
     * @return The current builder instance.
     */
    public PersonBuilder withSalutations(Salutation salutation) {
        this.salutation = salutation;
        return this;
    }


    /**
     * Sets the phone to the person.
     *
     * @param phone {String} The phone of the person.
     *
     * @return The current builder instance.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = phone;
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
