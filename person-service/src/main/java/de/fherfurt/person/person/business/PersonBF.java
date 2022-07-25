package de.fherfurt.person.person.business;

import de.fherfurt.person.core.persistence.DataController;
import de.fherfurt.person.person.entity.FileSystemRepository;
import de.fherfurt.person.person.entity.models.Image;
import de.fherfurt.person.person.entity.models.Person;
import de.fherfurt.person.person.entity.core.IPersonRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This business facade is used to work (means save, find and delete) {@link Person}s.
 *
 * @author Jonas Liehmann <jonas.liehmann@fh-erfurt.de>
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@RequiredArgsConstructor(staticName = "of")
public class PersonBF {

    private final IPersonRepository personRepository = DataController.getInstance().getPersonRepository();
    private final FilesBF filesBF = FilesBF.of();

    /**
     * Save an entity to the underlying storage. It doesn't matter, if the entity is new or already saved.
     * In case of update the changes are written too.
     *
     * @param person Instance to persist to database
     */
    public void save( final Person person ) {
        System.out.println(person);
        personRepository.save(person);
    }

    /**
     * Find all persisted entities.
     *
     * @return All persisted entities or an empty list
     */
    public List<Person> findAll() {
        return personRepository.findAll();
    }


    /**
     * Find an entity by its id.
     *
     * @param id The id of the searched entity
     * @return The found entity
     */
    public Person findBy( final int id ) {
        Person person = personRepository.findBy(id);
        System.out.println(person);
        return person;
    }

    /**
     * Find an entity by its email.
     *
     * @param email Email of the person
     * @return The found entity
     */
    public Person findByEmail( final String email ) {
        return personRepository.findByEmail( email );
    }

    /**
     * Find entities by its name.
     *
     * @param name The name (phrase) of the persons firstname or lastname or both.
     * @return The entities or an empty list
     */
    public List<Person> findByName( final String name ) {
        return personRepository.findByName( name );
    }

    /**
     * Find entities by its faculty id.
     *
     * @param id The faculty id of the person.
     * @return The entities or an empty list
     */
    public List<Person> findByFaculty( final int id ) {
        return personRepository.findByFaculty( id );
    }

    /**
     * Deletes a person by its id and the persons profile picture, if existing.
     *
     * @param id ID of the person to delete.
     */
    public void delete( final int id ) {
        final Person toDelete = findBy(id);

        try {
            filesBF.delete(FileSystemRepository.FileTypes.IMAGE, imgToName(toDelete.getProfileImage()));
        } catch (IOException ignored) { }

        personRepository.delete( id );
    }

    /**
     * Persists or updates a given profile image. The file will be replaced, if already existing.
     *
     * @param image   The image to store/update
     * @param content The content of the image
     *
     * @throws IOException Thrown if an error occurs while writing the file to the file system
     */
    public void saveImage(final Image image, byte[] content) throws IOException {
        final boolean newImage = image.getId() < 1;
        filesBF.save(FileSystemRepository.FileTypes.IMAGE, imgToName(image), content, newImage);
    }

    private String imgToName( final Image image ) {
        return image.getName() + "." + image.getSuffix();
    }

}
