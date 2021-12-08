import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person implements Comparable<Person> {
    private String phone;
    private ArrayList<String> titles;
    private String firstname;
    private String lastname;
    private String fax;
    private ArrayList<String> positions;
    private String email;
    private Faculty faculty;
    private Room room;
    private Salutation salutation;


    public Person() {
        titles = new ArrayList<>();
        positions = new ArrayList<>();
    }

    @Override
    public int compareTo(Person other) {
        int lastNameCompare = lastname.compareTo(other.lastname);
        if (lastNameCompare != 0) return lastNameCompare;

        return firstname.compareTo(other.firstname);
    }

    @Override
    public String toString() { return getName(); }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        final Person other = (Person) obj;

        if (!phone.equals(other.phone)) return false;
        if (!titles.equals(other.titles)) return false;
        if (!firstname.equals(other.firstname)) return false;
        if (!lastname.equals(other.lastname)) return false;
        if (!fax.equals(other.fax)) return false;
        if (!positions.equals(other.positions)) return false;
        if (!email.equals(other.email)) return false;
        if (!faculty.equals(other.faculty)) return false;
        if (!room.equals(other.room)) return false;

        return salutation.equals(other.salutation);
    }


    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String[] getTitles() {
        String[] arr = new String[titles.size()];
        arr = titles.toArray(arr);
        return arr;
    }
    public void setTitles(ArrayList<String> titles) { this.titles = new ArrayList<>(titles); }
    public void addTitle(String newTitle) { titles.add(newTitle); }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public String getName() { return this.firstname + " " + this.lastname; }

    public String getFax() { return fax; }
    public void setFax(String fax) { this.fax = fax; }

    public String[] getPositions() {
        String[] arr = new String[titles.size()];
        arr = positions.toArray(arr);
        return arr;
    }
    public void setPosition(ArrayList<String> positions) { this.positions = new ArrayList<>(positions); }
    public void addPosition(String newPosition) { positions.add(newPosition); }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Faculty getFaculty() { return faculty; }
    public void setFaculty(Faculty faculty) { this.faculty = faculty; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public Salutation getSalutation() { return salutation; }
    public void setSalutation(Salutation salutation) { this.salutation = salutation; }


    public boolean checkPhone(String phone) throws Exception { throw new Exception("Not Implemented Exception"); }
    public boolean checkFax(String fax) throws Exception { throw new Exception("Not Implemented Exception"); }
    public boolean checkEmail(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }
}