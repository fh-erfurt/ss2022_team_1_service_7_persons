package de.fherfurt.persons.client.objects;

import lombok.*;

/**
 * Container that holds all information of an image for the transport from the
 * <i>PERSON-Service</i> to another one.
 *
 * @author Tobias Kärst <tobias.kaerstn@fh-erfurt.de>
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with", toBuilder = true)
@EqualsAndHashCode( onlyExplicitlyIncluded = true )
public class ImageDto {
    /**
     * Unique identifier of an already persisted image.
     */
    private long id;

    /**
     * The name of the image file.
     */
    private String name;

    /**
     * The suffix (type) of the image file.
     */
    private String suffix;

    /**
     * The image represented as byte array.
     */
    private byte[] content;
}
