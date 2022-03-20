package de.fherfurt.person.core.persistence.errors;

import lombok.NoArgsConstructor;

/**
 * Generic exception that is thrown if any unexpected thing happened while database operations.
 *
 * @author Michael Rhoese <michael.rhoese@fh-erfurt.de>
 */
@NoArgsConstructor
public class PersistenceException extends RuntimeException {

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(Throwable cause) {
        super(cause);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}