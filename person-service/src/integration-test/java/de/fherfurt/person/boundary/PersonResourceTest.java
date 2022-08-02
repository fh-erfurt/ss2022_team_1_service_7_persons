package de.fherfurt.person.boundary;

import de.fherfurt.person.person.boundary.PersonResource;

import de.fherfurt.person.utils.DataProvider;
import de.fherfurt.person.utils.TestUtils;
import de.fherfurt.persons.client.objects.AccountDto;
import de.fherfurt.persons.client.objects.ImageDto;
import de.fherfurt.persons.client.objects.PersonDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class PersonResourceTest {
    private static final byte[] content = TestUtils.loadImage("demo-1.jpg");

    private static final PersonResource resource = PersonResource.of();

    @BeforeEach
    @AfterEach
    void cleanup() {
        resource.deleteAll();
    }

    @Test
    void shouldSaveNewPerson() {
        // GIVEN
        final PersonDto person = DataProvider.of().person( 1 );

        // WHEN
        resource.save( person );
        final PersonDto found = resource.findByEmail( person.getAccount().getEmail() ).orElse( null );

        // THEN
        Assertions.assertThat( found ).isEqualTo( person );
    }

    @Test
    void itShouldUpdatePersonIfExists() {
        // GIVEN
        final PersonDto person = DataProvider.of().person( 1 );

        // WHEN
        resource.save( person );
        final PersonDto savedPerson = resource.findByEmail( person.getAccount().getEmail() ).orElse( null );

        // THEN
        Assertions.assertThat( savedPerson ).isNotNull();

        // GIVEN
        final PersonDto updatedPerson = savedPerson.toBuilder()
                .withLastname( "Mustermann" )
                .build();

        // WHEN
        resource.save( updatedPerson );
        final PersonDto found = resource.findByEmail( updatedPerson.getAccount().getEmail() ).orElse( null );

        System.out.println(found);

        // THEN
        assert found != null;
        Assertions.assertThat( found.getLastname() ).isEqualTo( "Mustermann" );
    }

    @Test
    void itShouldDeletePerson() {
        // GIVEN
        final PersonDto person = DataProvider.of().person( 1 );

        // WHEN
        resource.save( person );
        final PersonDto savedPerson = resource.findByEmail( person.getAccount().getEmail() ).orElse( null );

        // THEN
        Assertions.assertThat( savedPerson ).isNotNull();
        Assertions.assertThat( resource.findAll( "", "" ).size() ).isEqualTo( 1 );
    }

    @Test
    void itShouldFindPersonById() {
        // GIVEN
        final PersonDto person = DataProvider.of().person( 1 );

        // WHEN
        resource.save( person );
        final PersonDto savedPerson = resource.findByEmail( person.getAccount().getEmail() ).orElse( null );
        assert savedPerson != null;
        final PersonDto foundPerson = resource.findById( savedPerson.getId() ).orElse( null );

        Assertions.assertThat( foundPerson ).isNotNull();
        Assertions.assertThat( foundPerson.getAccount().getEmail() ).isEqualTo(person.getAccount().getEmail());
    }

    @Test
    void itShouldDeletePersonById() {
        // GIVEN
        final PersonDto person = DataProvider.of().person( 1 );

        // WHEN
        resource.save( person );
        final PersonDto savedPerson = resource.findByEmail( person.getAccount().getEmail() ).orElse( null );
        assert savedPerson != null;
        resource.deleteBy( savedPerson.getId() );

        // THEN
        Assertions.assertThat( resource.findAll( "", "" ).size() ).isEqualTo( 0 );
    }

    @Test
    void itShouldFindPersonsAccountById() {
        // GIVEN
        final PersonDto person = DataProvider.of().person( 1 );

        // WHEN
        resource.save( person );
        final PersonDto savedPerson = resource.findByEmail( person.getAccount().getEmail() ).orElse( null );
        assert savedPerson != null;
        final AccountDto foundAccount = resource.findAccountById( savedPerson.getId() ).orElse( null );

        Assertions.assertThat( foundAccount ).isNotNull();
        Assertions.assertThat( foundAccount.getEmail() ).isEqualTo(person.getAccount().getEmail());
    }

    @Test
    void itShouldFindAllPersons() {
        // GIVEN
        final PersonDto person1 = DataProvider.of().person( 1 );
        final PersonDto person2 = DataProvider.of().person( 2 );

        // WHEN
        resource.save( person1 );
        resource.save( person2 );

        // THEN
        Assertions.assertThat( resource.findAll( "", "" ) ).isEqualTo( List.of( person1, person2 ) );
    }

    @Test
    void itShouldFindAllPersonsBySemester() {
        // GIVEN
        final PersonDto person1 = DataProvider.of().person( 1 );
        final PersonDto person2 = DataProvider.of().person( 2 );
        final PersonDto person3 = DataProvider.of().person( 3 );

        // WHEN
        resource.save( person1 );
        resource.save( person2 );
        resource.save( person3 );

        // THEN
        Assertions.assertThat( resource.findBySemester(2) ).isEqualTo( List.of( person1, person3 ) );
    }

    @Test
    void itShouldFindAllPersonsByFaculty() {
        // GIVEN
        final PersonDto person1 = DataProvider.of().person( 1 );
        final PersonDto person2 = DataProvider.of().person( 2 );

        // WHEN
        resource.save( person1 );
        resource.save( person2 );

        // THEN
        Assertions.assertThat( resource.findByFaculty( 420 ) ).isEqualTo( List.of( person2 ) );
    }

    @Test
    void itShouldFindPersonsByName() {
        // GIVEN
        final PersonDto person1 = DataProvider.of().person( 1 );
        final PersonDto person2 = DataProvider.of().person( 2 );
        final PersonDto person3 = DataProvider.of().person( 2 );

        // WHEN
        resource.save( person1 );
        resource.save( person2 );
        resource.save( person3 );

        // THEN
        Assertions.assertThat( resource.findByName( "To" ) ).isEqualTo( List.of( person2, person3 ) );
    }

    @Test
    void itShouldSaveLoadProfilePicture() {
        // GIVEN
        final PersonDto person = DataProvider.of().person( 1 );

        // WHEN
        resource.save( person );
        final PersonDto savedPerson = resource.findByEmail( person.getAccount().getEmail() ).orElse( null );
        resource.saveProfileImage( savedPerson, content );
        final PersonDto updatedPerson = resource.findByEmail( person.getAccount().getEmail() ).orElse( null );

        // THEN
        assert updatedPerson != null;
        Assertions.assertThat( updatedPerson.getProfileImage() ).isEqualTo( ImageDto.builder()
                        .withContent( content )
                        .withName( updatedPerson.getId().toString() )
                        .withSuffix( "jpg" )
                        .build() );
    }

    @Test
    void itShouldManuallyLoadProfilePicture() {
        // GIVEN
        final PersonDto person = DataProvider.of().person( 1 );

        // WHEN
        resource.save( person );
        final PersonDto savedPerson = resource.findByEmail( person.getAccount().getEmail() ).orElse( null );
        resource.saveProfileImage( savedPerson, content );
        assert savedPerson != null;
        final ImageDto foundImage = resource.loadPersonsImage( savedPerson.getId() ).orElse( null );

        // THEN
        assert foundImage != null;
        Assertions.assertThat(foundImage.getName()).isEqualTo(savedPerson.getId().toString());
    }
}
