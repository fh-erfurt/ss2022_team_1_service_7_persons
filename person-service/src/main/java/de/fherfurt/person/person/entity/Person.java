package de.fherfurt.person.person.entity;

import de.fherfurt.person.core.persistence.BaseBusinessEntity;
import lombok.*;

import java.util.List;

/**
 * Data object that holds the information from the database.
 *
 * @author Niklas Schumann <niklas.schumann@fh-erfurt.de>
 * @author Justin Noske <justin.noske@fh-erfurt.de>
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Person extends BaseBusinessEntity {

    private String email;
    private String salutation;
    private String firstname;
    private String lastname;
    private String username;
    private String phone;
    private String imageUrl;
    private String fax;
    private int facultyId;
    private List<String> titles;
    private List<String> positions;

    @Builder(setterPrefix = "with")
    public Person(int id, String email, String salutation, String firstname, String lastname, String username,
            String phone, String imageUrl, String fax, int facultyId, List<String> titles, List<String> positions) {
        super(id);
        this.email = email;
        this.salutation = salutation;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.fax = fax;
        this.facultyId = facultyId;
        this.titles = titles;
        this.positions = positions;
    }
}
