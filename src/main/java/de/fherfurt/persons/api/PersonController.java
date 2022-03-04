package de.fherfurt.persons.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.fherfurt.persons.models.Person;

/**
 * Module to simulate api access for a list of persons.
 */
public class PersonController {
    private static PersonController instance;
    private static int currId = 0;

    private ArrayList<Person> persons;

    /**
     * Returns the singleton instance of the person controller.
     *
     * @return The singleton instance.
     */
    public static PersonController getInstance() {
        if (instance == null) {
            instance = new PersonController();
        }

        return instance;
    }

    /**
     * Returns the next person id (auto increment).
     *
     * @return The next person id.
     */
    public static int getNextId() {
        return currId++;
    }

    /**
     * Request to safe the provided person.
     * 
     * @param person The person which should gets added.
     */
    public void create(Person person) {
        persons.add(person);
    }

    /**
     * Request to delete the provided person via id.
     * 
     * @param person The person which should gets deleted.
     * 
     * @return True if the person got removed, false instead.
     */
    public boolean delete(Person person) {
        return persons.removeIf(p -> p.getId() == person.getId());
    }

    /**
     * Request to update the provided person.
     * 
     * @param old    The person which should gets updated.
     * @param curr The updated person.
     * 
     * @return True if the person got updated, false instead.
     */
    public boolean update(Person old, Person curr) {
        int currPos = persons.indexOf(old);

        if (currPos < 0) {
            return false;
        }

        persons.set(currPos, curr);

        return true;
    }

    /**
     * Returns the list of all persons.
     * 
     * @return A list of all persons.
     */
    public List<Person> get() {
        return new ArrayList<>(persons);
    }

    /**
     * Checks if a person exists in the database.
     * 
     * @return True if the person exists, false instead.
     */
    public boolean exists(Person person) {
        return persons.contains(person);
    }

    /**
     * Returns the amount of persons in database.
     * 
     * @return The total amount of persons.
     */
    public int size() {
        return persons.size();
    }

    /**
     * Returns the person list as string representation.
     * 
     * @return The persons as string representation.
     */
    @Override
    public String toString() {
        return persons.toString();
    }

}
