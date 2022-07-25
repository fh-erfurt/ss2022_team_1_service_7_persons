package de.fherfurt.person.person.business;

import de.fherfurt.person.person.entity.FileSystemRepository;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Optional;

/**
 * This business facade encapsulates the handling of files from the whole core functionality.
 * It allows the storing and loading of internal files.
 *
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@NoArgsConstructor(staticName = "of")
public class FilesBF {
    private final FileSystemRepository fileSystemRepository = FileSystemRepository.of();

    /**
     * Saves a given file (content) under the specific subdirectory ({@link FileSystemRepository.FileTypes#name()}
     * for the given file name.
     *
     * @param type     Type of the file that must store
     * @param fileName File name of the file that must be stored
     * @param content  The content as byte array of the file
     * @param replace  Flag that signals that file has to be replaced
     *
     * @throws IOException Thrown if something goes wrong while deleting or writing
     */
    public void save( FileSystemRepository.FileTypes type, String fileName, byte[] content, boolean replace ) throws IOException {
        if ( replace ) {
            fileSystemRepository.delete( type, fileName );
        }

        fileSystemRepository.save( type, fileName, content );
    }

    /**
     * Searchs a file for its name and returns the content if found. If not, empty is returned.
     *
     * @param type     Type of the file that is searched
     * @param fileName File name of the file that is searched
     *
     * @return The file as byte array or empty
     * @throws IOException Thrown if something goes wrong while reading
     */
    public Optional<byte[]> findBy( FileSystemRepository.FileTypes type, String fileName ) throws IOException {
        return fileSystemRepository.findBy( type, fileName );
    }

    /**
     * Deletes a file by its name.
     *
     * @param type     Type of the file that must be deleted
     * @param fileName File name of the file that must be deleted
     *
     * @throws IOException Thrown if something goes wrong while deletion
     */
    public void delete(FileSystemRepository.FileTypes type, String fileName) throws IOException {
        fileSystemRepository.delete(type, fileName);
    }
}
