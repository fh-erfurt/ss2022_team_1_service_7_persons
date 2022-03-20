package de.fherfurt.person.core.persistence;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Base definition for every entity type that wants to be storable via a repository.
 *
 * @author Michael Rhoese <michael.rhoese@fh-erfurt.de>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseBusinessEntity {
    @EqualsAndHashCode.Include
    @Id
    private int id;
}