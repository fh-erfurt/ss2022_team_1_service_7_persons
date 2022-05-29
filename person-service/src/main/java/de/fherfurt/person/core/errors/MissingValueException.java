package de.fherfurt.person.core.errors;

import de.fherfurt.person.core.persistence.errors.PersistenceException;
import lombok.NoArgsConstructor;

/**
 * Thrown if a parameter of a function requires a non-null value, but it is null.
 */
@NoArgsConstructor
public class MissingValueException extends PersistenceException {
    public MissingValueException(String message) {
        super(message);
    }
}