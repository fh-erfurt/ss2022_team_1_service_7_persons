package de.fherfurt.person.person.boundary;

import de.fherfurt.person.core.mappers.BeanMapper;
import de.fherfurt.person.person.business.PersonBF;
import de.fherfurt.persons.client.PersonClient;
import de.fherfurt.persons.client.objects.PersonDto;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * This class represents the production implementation for the functionality of
 * the person submodule.
 *
 * @author Jonas Liehmann <johann.liehmann@fh-erfurt.de>
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@NoArgsConstructor(staticName = "of")
public class PersonResource implements PersonClient {
    private final PersonBF personBF = PersonBF.of();

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<PersonDto> findById(final int id) {
        return personBF.findBy(id).map(person -> (PersonDto) BeanMapper.mapToDto(person)).or(Optional::empty);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<PersonDto> findByEmail(String email) {
        return personBF.findByEmail(email).map(person -> (PersonDto) BeanMapper.mapToDto(person)).or(Optional::empty);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PersonDto> findByFaculty(int facultyId) {
        return (List<PersonDto>) personBF.findByFaculty(facultyId).stream().map(person -> (PersonDto) BeanMapper.mapToDto(person));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PersonDto> findByName(String name) {
        return (List<PersonDto>) personBF.findByName(name).stream().map(person -> (PersonDto) BeanMapper.mapToDto(person));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteBy(int id) {
        personBF.delete(id);
    }
}
