package de.fherfurt.person.person.business;

import de.fherfurt.person.core.persistence.DataController;
import de.fherfurt.person.person.entity.FileSystemRepository;
import de.fherfurt.person.person.entity.models.Image;
import de.fherfurt.person.person.entity.models.Person;
import de.fherfurt.person.person.entity.core.IPersonRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
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
        personRepository.save(person);
    }

    /**
     * Find all persisted entities.
     *
     * @return All persisted entities or an empty list
     */
    public List<Person> findAll() {
        return personRepository.findAll().stream().map(this::withImage).toList();
    }


    /**
     * Find an entity by its id.
     *
     * @param id The id of the searched entity
     * @return The found entity
     */
    public Person findBy( final long id ) {
        return withImage( personRepository.findBy(id) );
    }

    /**
     * Find an entity by its email.
     *
     * @param email Email of the person
     * @return The found entity
     */
    public Person findByEmail( final String email ) {
        return withImage( personRepository.findByEmail( email ) );
    }

    /**
     * Find entities by its name.
     *
     * @param name The name (phrase) of the persons firstname or lastname or both.
     * @return The entities or an empty list
     */
    public List<Person> findByName( final String name ) {
        return personRepository.findByName( name ).stream().map(this::withImage).toList();
    }

    /**
     * Find entities by its faculty id.
     *
     * @param id The faculty id of the person.
     * @return The entities or an empty list
     */
    public List<Person> findByFaculty( final int id ) {
        return personRepository.findByFaculty( id ).stream().map(this::withImage).toList();
    }

    /**
     * Deletes a person by its id and the persons profile picture, if existing.
     *
     * @param id ID of the person to delete.
     */
    public void delete( final long id ) {
        final Person toDelete = findBy(id);

        try {
            if (toDelete.getProfileImage() != null) {
                filesBF.delete(FileSystemRepository.FileTypes.IMAGE, imgToName(toDelete.getProfileImage()));
            }
        } catch (IOException ignored) { }

        personRepository.delete( id );
    }

    public void deleteAll() {
        final List<Person> toDelete = findAll();

        toDelete.forEach( ( person ) -> delete( person.getId() ) );
    }

    public void saveProfileImage( final Person person, final byte[] content) throws IOException {
        final Image image = Image.builder()
                .withContent(content)
                .withName(person.getId().toString())
                .withSuffix("jpg")
                .build();

        saveImage(image);

        person.setProfileImage(image);

        save(person);
    }

    /**
     * Persists or updates a given profile image. The file will be replaced, if already existing.
     *
     * @param image   The image to store/update
     *
     * @throws IOException Thrown if an error occurs while writing the file to the file system
     */
    public void saveImage(final Image image) throws IOException {
        filesBF.save(FileSystemRepository.FileTypes.IMAGE, imgToName(image), image.getContent(), true);
    }


    public Image loadImage(final Long imageId) throws IOException {
        final Optional<byte[]> content = filesBF.findBy(FileSystemRepository.FileTypes.IMAGE, imageId + "jpg");

        return Image.builder()
                .withName(imageId.toString())
                .withSuffix("jpg")
                .withContent(content.orElse(null))
                .build();
    }

    private String imgToName( final Image image ) {
        return image.getName() + "." + image.getSuffix();
    }

    private Person withImage( Person person ) {
        try {
            person.setProfileImage(loadImage(person.getId()));
        } catch (IOException ignored) { }

        return person;
    }

}
