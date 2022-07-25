package de.fherfurt.person.core.persistence;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Base definition for every entity type that wants to be storable via a repository.
 *
 * @author Tobias Kärst <tobias.kärst@fh-erfurt.de>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseBusinessEntity implements Serializable
{
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Version
    protected Long version;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date created;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date modified;

    @PrePersist
    void onCreate()
    {
        this.setCreated( new Date() );
    }

    @PreUpdate
    void onUpdate()
    {
        this.setModified( new Date() );
    }
}
