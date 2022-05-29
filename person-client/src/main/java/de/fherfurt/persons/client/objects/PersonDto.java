package de.fherfurt.persons.client.objects;

import lombok.*;

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
@Builder(setterPrefix = "with")
public class PersonDto {
    /**
     * Unique identifier of an already persisted person.
     */
    private int id;

    /**
     * The persons email address.
     */
    private String email;

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
     * The persons usernames.
     */
    private String username;

    /**
     * The persons phone number.
     */
    private String phone;

    /**
     * The persons profile picture url.
     */
    private String imageUrl;

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
    private ImageDto images;
}