package de.fherfurt.person.boundary;

import de.fherfurt.person.person.boundary.PersonResource;
import de.fherfurt.person.utils.DataProvider;
import de.fherfurt.person.utils.TestUtils;
import de.fherfurt.persons.client.objects.ImageDto;
import de.fherfurt.persons.client.objects.PersonDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class PersonResourceTest {
    private static final byte[] content = TestUtils.loadImage("demo-1.jpg");

    @Test
    void shouldSaveNewPerson() {
        // GIVEN
        final PersonResource resource = PersonResource.of();
        final PersonDto person = DataProvider.of().person(1);

        // WHEN
        resource.save(person);
        final PersonDto found = resource.findByEmail(person.getAccount().getEmail());

        // THEN
        Assertions.assertThat(found).isEqualTo(person);

        // CLEANUP
        resource.deleteAll();
        Assertions.assertThat(resource.findAll().size()).isEqualTo(0);
    }

    @Test
    void itShouldUpdatePersonIfExists() {
        // GIVEN
        final PersonResource resource = PersonResource.of();
        final PersonDto person = DataProvider.of().person(1);

        // WHEN
        resource.save(person);
        final PersonDto savedPerson = resource.findByEmail(person.getAccount().getEmail());

        System.out.println(savedPerson);

        // THEN
        Assertions.assertThat(savedPerson).isNotNull();

        // GIVEN
        final PersonDto updatedPerson = savedPerson.toBuilder()
                .withLastname("Mustermann")
                .build();

        // WHEN
        resource.save(updatedPerson);

        System.out.println(resource.findAll());

        final PersonDto found = resource.findByEmail(updatedPerson.getAccount().getEmail());

        // THEN
        Assertions.assertThat(found.getLastname()).isEqualTo("Mustermann");

        // CLEANUP
        resource.deleteAll();
        Assertions.assertThat(resource.findAll().size()).isEqualTo(0);
    }

    @Test
    void itShouldDeletePerson() {
        // GIVEN
        final PersonResource resource = PersonResource.of();
        final PersonDto person = DataProvider.of().person(1);

        // WHEN
        resource.save(person);
        final PersonDto savedPerson = resource.findByEmail(person.getAccount().getEmail());

        // THEN
        Assertions.assertThat(savedPerson).isNotNull();
        Assertions.assertThat(resource.findAll().size()).isEqualTo(1);

        // CLEANUP
        resource.deleteAll();
        Assertions.assertThat(resource.findAll().size()).isEqualTo(0);
    }

    @Test
    void itShouldFindAllPersons() {
        // GIVEN
        final PersonResource resource = PersonResource.of();
        final PersonDto person1 = DataProvider.of().person(1);
        final PersonDto person2 = DataProvider.of().person(2);

        // WHEN
        resource.save(person1);
        resource.save(person2);

        // THEN
        Assertions.assertThat(resource.findAll()).isEqualTo(List.of(person1, person2));

        // CLEANUP
        resource.deleteAll();
        Assertions.assertThat(resource.findAll().size()).isEqualTo(0);
    }

    @Test
    void itShouldFindAllPersonsByFaculty() {
        // GIVEN
        final PersonResource resource = PersonResource.of();
        final PersonDto person1 = DataProvider.of().person(1);
        final PersonDto person2 = DataProvider.of().person(2);

        // WHEN
        resource.save(person1);
        resource.save(person2);

        // THEN
        Assertions.assertThat(resource.findByFaculty(420)).isEqualTo(List.of(person2));

        // CLEANUP
        resource.deleteAll();
        Assertions.assertThat(resource.findAll().size()).isEqualTo(0);
    }

    @Test
    void itShouldFindPersonsByName() {
        // GIVEN
        final PersonResource resource = PersonResource.of();
        final PersonDto person1 = DataProvider.of().person(1);
        final PersonDto person2 = DataProvider.of().person(2);
        final PersonDto person3 = DataProvider.of().person(2);

        // WHEN
        resource.save(person1);
        resource.save(person2);
        resource.save(person3);

        // THEN
        Assertions.assertThat(resource.findByName("To")).isEqualTo(List.of(person2, person3));

        // CLEANUP
        resource.deleteAll();
        Assertions.assertThat(resource.findAll().size()).isEqualTo(0);
    }

    @Test
    void itShouldSaveLoadProfilePicture() throws IOException {
        // GIVEN
        final PersonResource resource = PersonResource.of();
        final PersonDto person = DataProvider.of().person(1);

        // WHEN
        resource.save(person);
        final PersonDto savedPerson = resource.findByEmail(person.getAccount().getEmail());
        resource.saveProfileImage(savedPerson, content);
        final PersonDto updatedPerson = resource.findByEmail(person.getAccount().getEmail());

        // THEN
        Assertions.assertThat(updatedPerson.getProfileImage()).isEqualTo(ImageDto.builder()
                        .withContent(content)
                        .withName(updatedPerson.getId().toString())
                        .withSuffix("jpg")
                        .build());

        // CLEANUP
        resource.deleteAll();
        Assertions.assertThat(resource.findAll().size()).isEqualTo(0);
    }
}
