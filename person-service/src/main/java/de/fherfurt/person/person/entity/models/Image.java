package de.fherfurt.person.person.entity.models;

import de.fherfurt.person.core.persistence.BaseBusinessEntity;
import lombok.*;

/**
 * Data object that holds the information from the database.
 *
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode( onlyExplicitlyIncluded = true, callSuper = true )
public class Image extends BaseBusinessEntity
{
    private String name;
    private String suffix;

    @Builder( setterPrefix = "with" )
    public Image( int id, String name, String suffix )
    {
        super( id );
        this.name = name;
        this.suffix = suffix;
    }
}