import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unused")
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
        phone = "";
        firstname = "";
        lastname = "";
        fax = "";
        email = "";
        faculty = null;
        room = null;
        salutation = null;
    }

    public Person(String phone, String[] titles, String firstname, String lastname, String fax, String[] positions, String email, Faculty faculty, Room room, Salutation salutation) {
        this.phone = phone;
        this.titles = new ArrayList<>(Arrays.asList(titles));
        this.firstname = firstname;
        this.lastname = lastname;
        this.fax = fax;
        this.positions = new ArrayList<>(Arrays.asList(positions));
        this.email = email;
        this.faculty = null;
        this.room = null;
        this.salutation = salutation;
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
        if (faculty != null && !faculty.equals(other.faculty)) return false;
        if (room != null && !room.equals(other.room)) return false;

        return salutation.equals(other.salutation);
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String[] getTitles() { return titles.toArray(new String[0]); }
    public void setTitles(String[] titles) { this.titles = new ArrayList<>(Arrays.asList(titles)); }
    public void addTitle(String newTitle) { titles.add(newTitle); }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getName() { return this.firstname + " " + this.lastname; }

    public String getFax() { return fax; }
    public void setFax(String fax) { this.fax = fax; }

    public String[] getPositions() { return positions.toArray(new String[0]); }
    public void setPositions(String[] positions) { this.positions = new ArrayList<>(Arrays.asList(positions)); }
    public void addPosition(String newPosition) { positions.add(newPosition); }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Faculty getFaculty() { return faculty; }
    public void setFaculty(Faculty faculty) { this.faculty = faculty; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public Salutation getSalutation() { return salutation; }
    public void setSalutation(Salutation salutation) { this.salutation = salutation; }

}