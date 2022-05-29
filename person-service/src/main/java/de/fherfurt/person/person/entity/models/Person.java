package de.fherfurt.person.person.entity.models;

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
@EqualsAndHashCode( onlyExplicitlyIncluded = true, callSuper = true )
public class Person extends BaseBusinessEntity
{
    private String salutation;
    private String firstname;
    private String lastname;
    private String phone;
    private String fax;
    private int facultyId;
    private List<String> titles;
    private List<String> positions;
    private Account account;
    private Image profileImage;

    @Builder( setterPrefix = "with" )
    public Person( int id, String salutation, String firstname, String lastname, String phone, String fax, int facultyId, List<String> titles, List<String> positions, Account account, Image profileImage )
    {
        super( id );
        this.salutation = salutation;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.fax = fax;
        this.facultyId = facultyId;
        this.titles = titles;
        this.positions = positions;
        this.account = account;
        this.profileImage = profileImage;
    }
}
