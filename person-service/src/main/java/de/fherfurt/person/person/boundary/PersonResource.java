package de.fherfurt.person.person.boundary;

import de.fherfurt.person.core.mappers.BeanMapper;
import de.fherfurt.person.person.business.PersonBF;
import de.fherfurt.person.person.entity.models.Person;
import de.fherfurt.persons.client.PersonClient;
import de.fherfurt.persons.client.objects.AccountDto;
import de.fherfurt.persons.client.objects.ImageDto;
import de.fherfurt.persons.client.objects.PersonDto;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class represents the production implementation for the functionality of
 * the person submodule.
 *
 * @author Jonas Liehmann <jonas.liehmann@fh-erfurt.de>
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@NoArgsConstructor( staticName = "of" )
public class PersonResource implements PersonClient {
    private final PersonBF personBF = PersonBF.of();

    /**
     * Takes the id of a {@link PersonDto} and searches for its user account. If the corresponding {@link AccountDto}
     * is found, it will be returned, otherwise an empty {@link Optional} is returned.
     *
     * @param id The ID of the person to find the account for.
     * @return The found account with matching user id.
     */
    @Override
    public Optional<AccountDto> findAccountById( int id ) {
        return Optional.empty();
    }

    /**
     * Takes the id of a {@link PersonDto} and loads the related {@link ImageDto} which represents the persons
     * profile image. This {@link ImageDto} contains the image as byte array. If no related {@link ImageDto} was found,
     * an empty {@link Optional} is returned.
     *
     * @param id The ID of the person to find the account for.
     * @return The found account with matching user id.
     */
    @Override
    public Optional<ImageDto> loadPersonsImage(int id) {
        return Optional.empty();
    }

    /**
     * Find all persisted entities.
     *
     * @return All persisted entities or an empty list
     */
    public List<PersonDto> findAll() {
        return personBF.findAll().stream().map(person -> (PersonDto) BeanMapper.mapToDto(person)).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonDto findById(final int id) {
        return BeanMapper.mapToDto(personBF.findBy(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonDto findByEmail(String email) {
        return BeanMapper.mapToDto(personBF.findByEmail(email));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PersonDto> findByFaculty(int facultyId) {
        return personBF.findByFaculty(facultyId).stream().map(person -> (PersonDto) BeanMapper.mapToDto(person)).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PersonDto> findByName(String name) {
        return personBF.findByName(name).stream().map(person -> (PersonDto) BeanMapper.mapToDto(person)).collect(Collectors.toList());
    }

    /**
     * Takes the semester of a {@link PersonDto} and searches all persons holding
     * lectures in this semester. If no persons match semester (e.g. wrong input: -1),
     * an empty {@link List} is returned.
     *
     * @param semester The semester to search for matching lecturers.
     * @return The found persons as list.
     */
    @Override
    public List<PersonDto> findBySemester(int semester) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteBy(int id) {
        personBF.delete(id);
    }

    public void deleteAll() {
        personBF.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(PersonDto person) {
        personBF.save(BeanMapper.mapFromDto(person));
    }

    public void saveProfileImage(final PersonDto person, final byte[] content) throws IOException {
        personBF.saveProfileImage(BeanMapper.mapFromDto(person), content);
    }
}
