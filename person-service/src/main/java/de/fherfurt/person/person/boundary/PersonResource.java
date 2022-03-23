package de.fherfurt.person.person.boundary;

import de.fherfurt.person.core.mappers.BeanMapper;
import de.fherfurt.person.person.business.PersonBF;
import de.fherfurt.persons.client.PersonClient;
import de.fherfurt.persons.client.objects.PersonDto;
import lombok.NoArgsConstructor;

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
     * {@inheritDoc}
     */
    @Override
    public void deleteBy(int id) {
        personBF.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(PersonDto person) {
        personBF.save(BeanMapper.mapFromDto(person));
    }
}
