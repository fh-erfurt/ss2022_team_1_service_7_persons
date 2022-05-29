package de.fherfurt.person.person.entity;

import de.fherfurt.person.core.errors.MissingValueException;
import de.fherfurt.person.person.entity.errors.MissingContentException;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

/**
 * This repository provides the API to the file system and allows basic CRUD operations. The files are sorted into
 * subdirectories dependent on the {@link FileTypes}. Currently, the temp directory is used. This must be changed
 * after migrating to a server framework.
 *
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@NoArgsConstructor( staticName = "of" )
public class FileSystemRepository
{
    public static final String BASE_DIR = System.getProperty( "java.io.tmpdir" );
    private final String serviceDir = "person";

    /**
     * Persists or update a given file. If the file content is already persisted as file, the file will be removed a
     * newly created.
     *
     * @param type     Type of the file which must be saved
     * @param fileName Filename (with suffix) of the file
     * @param content  The file content as byte array
     * @throws IOException Thrown if something while writing the file to the file system
     */
    public void save( final FileTypes type, final String fileName, final byte[] content ) throws IOException
    {
        if ( Objects.isNull( type ) )
            throw new MissingValueException( "Type of file is missing" );

        if ( Objects.isNull( content ) || content.length == 0 )
            throw new MissingContentException( "No content to store" );

        Path usedDirectory = Paths.get( BASE_DIR, serviceDir, type.name().toLowerCase() );
        if ( Files.notExists( usedDirectory ) )
            Files.createDirectories( usedDirectory );
        final Path file = Paths.get( usedDirectory.toString(), fileName) ;

        if ( Files.exists( file ) )
            throw new FileAlreadyExistsException( "Could not store file '" + file + "'. Already exists" );

        Files.write( file, content );
    }

    /**
     * Finds a file by its type and filename and returns the loaded content of the file.
     *
     * @param type      Type of the searched file
     * @param fileName  Filename (with suffix) of the file
     * @return The content of the file as byte array or empty
     * @throws IOException Thrown if an error occurs while reading the file
     */
    public Optional<byte[]> findBy( final FileTypes type, final String fileName ) throws IOException
    {
        Path file = Paths.get( BASE_DIR, serviceDir, type.name().toLowerCase(), fileName );

        if ( Files.notExists( file ) )
            return Optional.empty();

        return Optional.of( Files.readAllBytes( file ) );
    }

    /**
     * Deletes a file by its type and filename.
     *
     * @param type     Type of the file which must be deleted
     * @param fileName Filename (with suffix) of the file
     * @throws IOException Thrown if an error occurs while deleting the file
     */
    public void delete( final FileTypes type, final String fileName ) throws IOException
    {
        Path file = Paths.get( BASE_DIR, serviceDir, type.name().toLowerCase(), fileName );

        if ( Files.notExists( file ) )
            return;

        Files.delete( file );
    }

    /**
     * Enum that defines possible file types this repository can persist on the system.
     */
    public enum FileTypes {
        IMAGE
    }
}
