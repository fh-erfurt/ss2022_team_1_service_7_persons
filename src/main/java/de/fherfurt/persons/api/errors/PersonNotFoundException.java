package de.fherfurt.persons.api.errors;

/**
 * Class representing an Exception thrown if no person was found.
 */
public class PersonNotFoundException extends Exception {
    /**
     * Creates an instance of PersonNotFoundException.
     *
     * @param msg The error message which should get thrown.
     */
    public PersonNotFoundException(String msg) {
        super(msg);
    }
}