package de.fherfurt.person.person.entity.models;

import de.fherfurt.person.core.persistence.BaseBusinessEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Data object that holds the information from the database.
 *
 * @author Niklas Schumann <niklas.schumann@fh-erfurt.de>
 * @author Justin Noske <justin.noske@fh-erfurt.de>
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode( callSuper = true )
@Builder( setterPrefix = "with", toBuilder = true )
@Entity
@Table
public class Person extends BaseBusinessEntity
{
    private String salutation;
    private String firstname;
    private String lastname;
    private String phone;
    private String fax;
    private int facultyId;

    @ElementCollection
    private List<String> titles;

    @ElementCollection
    private List<String> positions;

    @OneToOne( cascade = CascadeType.PERSIST )
    private Account account;

    @OneToOne( cascade = CascadeType.PERSIST )
    private Image profileImage;
}
