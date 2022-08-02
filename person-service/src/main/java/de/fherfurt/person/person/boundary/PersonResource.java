package de.fherfurt.person.person.boundary;

import de.fherfurt.person.core.mappers.BeanMapper;
import de.fherfurt.person.person.business.PersonBF;
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

    /** {@inheritDoc} */
    @Override
    public Optional<AccountDto> findAccountById( final long id ) {
        try {
            return Optional.of( BeanMapper.mapToDto(personBF.findBy( id ).getAccount() ) );
        } catch ( Exception ignored ) {
            return Optional.empty();
        }
    }

    /** {@inheritDoc} */
    @Override
    public Optional<ImageDto> loadPersonsImage( final long id ) {
        try {
            return Optional.of( BeanMapper.mapToDto( personBF.loadImage( id ) ) );
        } catch ( IOException ignored ) {
            return Optional.empty();
        }
    }

    /** {@inheritDoc} */
    @Override
    public void saveProfileImage( final PersonDto person, final byte[] content ) {
        try {
            personBF.saveProfileImage(BeanMapper.mapFromDto(person), content);
        } catch ( IOException ignored ) {}
    }

    /** {@inheritDoc} */
    public List<PersonDto> findAll( String sortBy, String orderBy ) {
        return personBF.findAll( sortBy, orderBy )
                .stream()
                .map(person -> (PersonDto) BeanMapper.mapToDto(person))
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public Optional<PersonDto> findById( final long id ) {
        try {
            return Optional.of( BeanMapper.mapToDto( personBF.findBy( id ) ) );
        } catch ( Exception ignored ) {
            return Optional.empty();
        }
    }

    /** {@inheritDoc} */
    @Override
    public Optional<PersonDto> findByEmail( String email ) {
        try {
            return Optional.of( BeanMapper.mapToDto( personBF.findByEmail( email ) ) );
        } catch ( Exception ignored ) {
            return Optional.empty();
        }
    }

    /** {@inheritDoc} */
    @Override
    public List<PersonDto> findByFaculty(int facultyId) {
        return personBF.findByFaculty( facultyId )
                .stream()
                .map( person -> ( PersonDto ) BeanMapper.mapToDto( person ) )
                .collect( Collectors.toList() );
    }

    /** {@inheritDoc} */
    @Override
    public List<PersonDto> findByName( String name ) {
        return personBF.findByName( name )
                .stream()
                .map( person -> ( PersonDto ) BeanMapper.mapToDto( person ) )
                .collect( Collectors.toList() );
    }

    /** {@inheritDoc} */
    @Override
    public List<PersonDto> findBySemester( final int semester ) {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void deleteBy( final long id ) {
        personBF.delete(id);
    }

    /** {@inheritDoc} */
    public void deleteAll() {
        personBF.deleteAll();
    }

    /** {@inheritDoc} */
    @Override
    public void save(PersonDto person) {
        personBF.save(BeanMapper.mapFromDto(person));
    }

}
