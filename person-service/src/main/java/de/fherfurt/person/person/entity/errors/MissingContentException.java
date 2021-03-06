package de.fherfurt.person.person.entity.errors;

import de.fherfurt.person.core.persistence.exceptions.PersistenceException;
import lombok.NoArgsConstructor;

/**
 * Thrown if the content (byte array of a file) is missing but required.
 *
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@NoArgsConstructor
public class MissingContentException extends PersistenceException
{
    public MissingContentException( String message )
    {
        super( message );
    }
}
