package de.fherfurt.person.person.entity.models;

import de.fherfurt.person.core.persistence.BaseBusinessEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Data object that holds the information from the database.
 *
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode( callSuper = true )
@Entity
@Builder( setterPrefix = "with" )
@Table
public class Image extends BaseBusinessEntity
{
    private String name;
    private String suffix;
}