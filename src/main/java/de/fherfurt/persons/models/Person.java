package de.fherfurt.persons.models;

import org.json.JSONObject;

import de.fherfurt.persons.api.PersonController;

/**
 * Class representating the person data model.
 * 
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
public class Person implements Comparable<Person> {
    private int id;
    private int faculty;
    private String firstname;
    private String lastname;

    /**
     * Creates an instance of a person with id.
     *
     * @param id        The id of the person.
     * @param firstname The firstname of the person.
     * @param lastname  The lastname of the person.
     */
    public Person(int id, int faculty, String firstname, String lastname) {
        this.id = id;
        this.faculty = faculty;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * Creates an instance of a person.
     *
     * @param firstname The firstname of the person.
     * @param lastname  The lastname of the person.
     */
    public Person(int faculty, String firstname, String lastname) {
        this.id = PersonController.getNextId();
        this.faculty = faculty;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * Get the firstname of the person
     *
     * @return The persons firstname.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Get the lastname of the person
     *
     * @return The persons lastname.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Get the full name of the person
     *
     * @return The persons full name.
     */
    public String getName() {
        return firstname + " " + lastname;
    }

    /**
     * Get the faculty id of the person
     *
     * @return The persons faculty id.
     */
    public int getFaculty() {
        return faculty;
    }

    /**
     * Get the id of the person
     *
     * @return The persons id.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the person as json string representation.
     *
     * @return The json representation of the person.
     */
    public String toJSON() {
        return new JSONObject()
                .put("firstname", firstname)
                .put("lastname", lastname)
                .put("faculty", faculty)
                .put("id", id)
                .toString();
    }

    /**
     * Returns the person data as string representation.
     *
     * @return The string representation of the person.
     */
    @Override
    public String toString() {
        return firstname + " " + lastname;
    }

    /**
     * Compares both objects and returns true, if the values are the same.
     *
     * @return Returns true, if the values matches, false instead.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        final Person other = (Person) obj;

        return firstname.equals(other.firstname)
                && lastname.equals(other.lastname)
                && faculty == other.getFaculty();
    }

    /**
     * Compares both objects via lastname. If the lastname is the same, it compares
     * via firstname. The method returns a comparing token (-1, 0, 1).
     *
     * @return Returns -1, if the provided name of the obj is
     *         lexicographical greater than the current one, 0 if they are the same
     *         or 1, if the current one is smaller.
     */
    @Override
    public int compareTo(Person other) {
        int lastNameCompare = lastname.compareTo(other.lastname);
        if (lastNameCompare != 0) {
            return lastNameCompare;
        }

        return firstname.compareTo(other.firstname);
    }
}
