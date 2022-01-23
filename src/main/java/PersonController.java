import java.util.ArrayList;

public class PersonController {
    private ArrayList<Person> persons;


    public void addPerson(Person newPerson) { persons.add(newPerson); }

    public void deletePerson(Person person){
        persons.removeIf(p -> p.getId() == person.getId());
    }

    public void createPerson(int id, String phone, String[] titles, String firstname, String lastname, String fax, String[] positions, String email, int faculty, Room room, Salutation salutation) {
        Person person = new Person(id, phone, titles, firstname, lastname, fax, positions,email, faculty, room, salutation);
        addPerson(person);
    }

    public void updatePerson(Person person1, Person person2){
        persons.removeIf(p -> p.getId() == person1.getId());
        persons.add(person2);
    }

    public boolean exists(Person person){
        return persons.contains(person);
    }

    public int size(){
        return persons.size();
    }

    public Person[] getPersons() {
        return persons.toArray(new Person[0]);
    }

    public void sort(SortType type){
        if(type==SortType.Faculty)
        {
            SortingModule.sortPersonsByFaculty(getPersons());
        }
        else if(type==SortType.Firstname)
        {
            SortingModule.sortPersonsByFirstname(getPersons());
        }
        else if(type==SortType.Lastname)
        {
            SortingModule.sortPersonsByLastname(getPersons());
        }
        else if(type==SortType.Name)
        {
            SortingModule.sortPersonsByName(getPersons());
        }
        else if(type==SortType.Position)
        {
            SortingModule.sortPersonsByPosition(getPersons());
        }
    }

    public Person[] search(SearchType type, String token) throws PersonNotFoundException {
        if(type==SearchType.ID)
        {
            return SearchingModule.getPersonByID(getPersons(),Integer.parseInt(token));
        }
        else if(type==SearchType.FacultyID)
        {
            return SearchingModule.getPersonsByFacultyID(getPersons(),Integer.parseInt(token));
        }
        else /*if(type==SearchType.Name)*/
        {
            return SearchingModule.getPersonsByName(getPersons(),token);
        }
    }

    public String toString(){
        return persons.toString();
    }
}
