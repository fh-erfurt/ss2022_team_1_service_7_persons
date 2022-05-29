package de.fherfurt.person.person.entity.errors;

import de.fherfurt.person.core.persistence.errors.PersistenceException;
import lombok.NoArgsConstructor;

/**
 * Thrown if the content (byte array of a file) is missing but required.
 */
@NoArgsConstructor
public class MissingContentException extends PersistenceException
{
    public MissingContentException( String message )
    {
        super( message );
    }
}
