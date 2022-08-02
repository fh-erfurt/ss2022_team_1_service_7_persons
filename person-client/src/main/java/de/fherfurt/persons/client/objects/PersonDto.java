package de.fherfurt.persons.client.objects;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Container that holds all information of a person for the transport from the
 * <i>PERSON-Service</i> to another one.
 *
 * @author Niklas Schumann <niklas.schumann@fh-erfurt.de>
 * @author Justin Noske <justin.noske@fh-erfurt.de>
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with", toBuilder = true)
@EqualsAndHashCode( onlyExplicitlyIncluded = true )
public class PersonDto {
    /**
     * Unique identifier of an already persisted person.
     */
    private Long id = 0L;

    /**
     * The database version.
     */
    private Long version;

    /**
     * The persons salutation.
     */
    private String salutation;

    /**
     * The persons firstname.
     */
    private String firstname;

    /**
     * The persons lastname.
     */
    private String lastname;

    /**
     * The persons phone number.
     */
    private String phone;

    /**
     * The persons fax number.
     */
    private String fax;

    /**
     * The persons faculty id.
     */
    private int facultyId;

    /**
     * A list of the person's titles
     */
    private List<String> titles;

    /**
     * A list of the person's positions in university.
     */
    private List<String> positions;

    /**
     * The account of the person.
     */
    private AccountDto account;

    /**
     * The persons profile image (can be null).
     */
    private ImageDto profileImage;

    public boolean isValidToCreate() {
        return (
            this.getFirstname() != null &&
            this.getLastname() != null &&
            this.getFacultyId() != 0 &&
            this.getAccount().isValidToCreate()
        );
    }

    public void merge(PersonDto newPerson) {
        this.salutation = newPerson.getSalutation() != null ? newPerson.getSalutation() : this.salutation;
        this.firstname = newPerson.getFirstname() != null ? newPerson.getFirstname() : this.firstname;
        this.lastname = newPerson.getLastname() != null ? newPerson.getLastname() : this.lastname;
        this.phone = newPerson.getPhone() != null ? newPerson.getPhone() : this.phone;
        this.fax = newPerson.getFax() != null ? newPerson.getFax() : this.fax;
        this.facultyId = newPerson.getFacultyId() != 0 ? newPerson.getFacultyId() : this.facultyId;
        this.titles = newPerson.getTitles() != null ? newPerson.getTitles() : this.titles;
        this.positions = newPerson.getPositions() != null ? newPerson.getPositions() : this.positions;

        if (newPerson.getAccount() != null) this.getAccount().merge(newPerson.getAccount());
        if (newPerson.getProfileImage() != null) this.getProfileImage().merge(newPerson.getProfileImage());
    }
}