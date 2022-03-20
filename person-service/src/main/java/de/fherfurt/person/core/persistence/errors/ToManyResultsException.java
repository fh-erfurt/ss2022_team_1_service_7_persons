package de.fherfurt.person.core.persistence.errors;

import lombok.NoArgsConstructor;

/**
 * Thrown if a database request returns more than one result.
 *
 * @author Michael Rhoese <michael.rhoese@fh-erfurt.de>
 */
@NoArgsConstructor
public class ToManyResultsException extends PersistenceException {
    public ToManyResultsException(String message) {
        super(message);
    }
}
