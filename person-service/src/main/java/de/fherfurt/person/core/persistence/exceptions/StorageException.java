package de.fherfurt.person.core.persistence.exceptions;

public class StorageException extends Exception
{
    public StorageException( String message )
    {
        super( message );
    }

    public StorageException( Throwable cause )
    {
        super( cause );
    }

    public StorageException( String message, Throwable cause )
    {
        super( message, cause );
    }
}