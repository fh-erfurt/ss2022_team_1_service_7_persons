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
}