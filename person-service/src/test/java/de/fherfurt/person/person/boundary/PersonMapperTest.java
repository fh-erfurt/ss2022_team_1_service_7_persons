package de.fherfurt.person.person.boundary;

import de.fherfurt.person.core.mappers.BeanMapper;
import de.fherfurt.person.person.entity.models.Account;
import de.fherfurt.person.person.entity.models.Image;
import de.fherfurt.person.person.entity.models.Person;
import de.fherfurt.persons.client.objects.AccountDto;
import de.fherfurt.persons.client.objects.ImageDto;
import de.fherfurt.persons.client.objects.PersonDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
public class PersonMapperTest {

    @Test
    void shouldMapCompletePersonEntityToDto() {
        // GIVEN
        final Person entity = Person.builder()
                .withAccount( Account.builder()
                        .withEmail( "cfaunch0@symantec.com" )
                        .withPassword( "1234" )
                        .withUsername( "CorissaMustermann42" )
                        .build()

                )
                .withProfileImage( Image.builder().
                        withName( "hello-world" )
                        .build()
                )
                .withSalutation( "Frau" )
                .withPhone( "01575 31834128" )
                .withFax( "04613 18231247" )
                .withTitles( List.of( "Dr", "Prof" ) )
                .withPositions( List.of( "Dozentin" ) )
                .withFirstname( "Corissa" )
                .withLastname( "Mustermann" )
                .withFacultyId( 123 )
                .build();

        // WHEN
        final PersonDto result = BeanMapper.mapToDto( entity );

        // THEN
        Assertions.assertEquals( result.getPhone(), "01575 31834128" );
        Assertions.assertEquals( result.getTitles(), List.of( "Dr", "Prof" ) );
        Assertions.assertEquals( result.getAccount().getEmail(), "cfaunch0@symantec.com" );
        Assertions.assertEquals( result.getProfileImage().getName(), "hello-world" );
    }

    @Test
    void shouldMapCompletePersonDtoToEntity() {
        // GIVEN
        final PersonDto dto = PersonDto.builder()
                .withAccount( AccountDto.builder()
                        .withEmail( "cfaunch0@symantec.com" )
                        .withPassword( "1234" )
                        .withUsername( "CorissaMustermann42" )
                        .build()

                )
                .withProfileImage( ImageDto.builder().
                        withName( "hello-world" )
                        .build()
                )
                .withSalutation( "Frau" )
                .withPhone( "01575 31834128" )
                .withFax( "04613 18231247" )
                .withTitles( List.of( "Dr", "Prof") )
                .withPositions( List.of( "Dozentin") )
                .withFirstname( "Corissa" )
                .withLastname( "Mustermann" )
                .withFacultyId( 123 )
                .build();

        // WHEN
        final Person result = BeanMapper.mapFromDto( dto );

        // THEN
        Assertions.assertEquals( result.getPhone(), "01575 31834128" );
        Assertions.assertEquals( result.getTitles(), List.of( "Dr", "Prof" ) );
        Assertions.assertEquals( result.getAccount().getEmail(), "cfaunch0@symantec.com" );
        Assertions.assertEquals( result.getProfileImage().getName(), "hello-world" );
    }

}
