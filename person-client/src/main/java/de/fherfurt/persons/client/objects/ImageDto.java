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
    private Long id = 0L;

    /**
     * The database version.
     */
    private Long version;

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

    public void merge(ImageDto newImage) {
        this.name = newImage.getName() != null ? newImage.getName() : this.name;
        this.suffix = newImage.getSuffix() != null ? newImage.getSuffix() : this.suffix;
        this.content = newImage.getContent() != null ? newImage.getContent() : this.content;
    }
}
