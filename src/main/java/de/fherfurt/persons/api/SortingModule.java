package de.fherfurt.persons.api;

import de.fherfurt.persons.models.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Data object that holds the information from the database.
 *
 * @author Jonas Liehmann <jonas.liehmann@fh-erfurt.de>
 */
class PersonFirstnameComparator implements Comparator<Person> {

    /**
     * Implements a new Comparator of given type Person to sort by Firstname.
     *
     * @param person1 {Person} - The first person to compare against
     * @param person2 {Person} - The second person to compare against the first person
     */
    @Override
    public int compare(Person person1, Person person2) {
        return String.CASE_INSENSITIVE_ORDER.compare(person1.getFirstname(), person2.getFirstname());
    }
}
class PersonLastnameComparator implements Comparator<Person> {
    /**
     * Implements a new Comparator of given type Person to sort by Lastname.
     *
     * @param person1 {Person} - The first person to compare against
     * @param person2 {Person} - The second person to compare against the first person
     */
    @Override
    public int compare(Person person1, Person person2) {
        return String.CASE_INSENSITIVE_ORDER.compare(person1.getLastname(), person2.getLastname());
    }
}
class PersonNameComparator implements Comparator<Person> {
    /**
     * Implements a new Comparator of given type Person to sort by Name.
     *
     * @param person1 {Person} - The first person to compare against
     * @param person2 {Person} - The second person to compare against the first person
     */
    @Override
    public int compare(Person person1, Person person2) {
        return String.CASE_INSENSITIVE_ORDER.compare(person1.getName(), person2.getName());
    }
}
class PersonPositionComparator implements Comparator<Person> {
    /**
     * Implements a new Comparator of given type Person to sort by Position.
     * Joins all Positions of the first person and compares it aginst the second person.
     *
     * @param person1 {Person} - The first person to compare against
     * @param person2 {Person} - The second person to compare against the first person
     */
    @Override
    public int compare(Person person1, Person person2) {
        List<String> positions1 = person1.getPositions();
        List<String> positions2 = person2.getPositions();

        Collections.sort(positions1);
        Collections.sort(positions2);

        String positionsConcated1 = String.join(" ", positions1);
        String positionsConcated2 = String.join(" ", positions2);

        return String.CASE_INSENSITIVE_ORDER.compare(positionsConcated1, positionsConcated2);
    }
}
class PersonFacultyComparator implements Comparator<Person> {
    /**
     * Implements a new Comparator of given type Person to sort by Faculty.
     *
     * @param person1 {Person} - The first person to compare against
     * @param person2 {Person} - The second person to compare against the first person
     *
     */
    @Override
    public int compare(Person person1, Person person2) {
        return String.CASE_INSENSITIVE_ORDER.compare(person1.getFaculty(), person2.getFaculty());
    }
}

public class SortingModule {

    /**
     * Sorts the given Person Array by Lastname.
     *
     * @param _persons {Person[]} - The persons array to sort
     */
    public static void sortPersonsByLastname(Person[] _persons) {
        Arrays.sort(_persons, new PersonLastnameComparator());
    }

    /**
     * Sorts the given Person Array by Firstname.
     *
     * @param _persons {Person[]} - The persons array to sort
     */
    public static void sortPersonsByFirstname(Person[] _persons) {
        Arrays.sort(_persons, new PersonFirstnameComparator());
    }

    /**
     * Sorts the given Person Array by Name.
     *
     * @param _persons {Person[]} - The persons array to sort
     */
    public static void sortPersonsByName(Person[] _persons) {
        Arrays.sort(_persons, new PersonNameComparator());
    }

    /**
     * Sorts the given Person Array by Position.
     *
     * @param _persons {Person[]} - The persons array to sort
     */
    public static void sortPersonsByPosition(Person[] _persons) {
        Arrays.sort(_persons, new PersonPositionComparator());
    }

    /**
     * Sorts the given Person Array by Faculty.
     *
     * @param _persons {Person[]} - The persons array to sort
     */
    public static void sortPersonsByFaculty(Person[] _persons) {
        Arrays.sort(_persons, new PersonFacultyComparator());
    }
}