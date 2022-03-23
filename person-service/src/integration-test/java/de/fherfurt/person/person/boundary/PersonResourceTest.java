package de.fherfurt.person.person.boundary;

import de.fherfurt.persons.client.objects.PersonDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static de.fherfurt.person.TestTags.BOUNDARY;
import static de.fherfurt.person.TestTags.DOMAIN;
import static de.fherfurt.person.TestTags.INTEGRATION;

/**
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@Tags({
        @Tag(INTEGRATION),
        @Tag(DOMAIN),
        @Tag(BOUNDARY),
})
public class PersonResourceTest {
    @Test
    void shouldSaveNewPerson() {
        // GIVEN
        final PersonDto person = PersonDto.builder()
                .withEmail("cfaunch0@symantec.com")
                .withFirstname("Corissa")
                .withLastname("Faunch")
                .withId(1)
                .withFacultyId(123)
                .build();

        final PersonResource resource = PersonResource.of();

        // WHEN
        resource.save(person);
        final Optional<PersonDto> found = resource.findById(1);

        // THEN
        Assertions.assertThat(found).isPresent();
        Assertions.assertThat(found.get().getEmail()).isEqualTo("cfaunch0@symantec.com");
    }

    @Test
    void shouldDeletePerson() {
        // GIVEN
        final PersonDto person = PersonDto.builder().withId(1).build();
        final PersonResource resource = PersonResource.of();

        // WHEN
        resource.save(person);
        resource.deleteBy(1);
        final Optional<PersonDto> found = resource.findById(1);

        // THEN
        Assertions.assertThat(found).isEmpty();
    }

    @Test
    void shouldFindPersonById() {
        // GIVEN
        final PersonDto person = PersonDto.builder().withId(1).build();
        final PersonResource resource = PersonResource.of();

        // WHEN
        resource.save(person);
        final Optional<PersonDto> found = resource.findById(1);

        // THEN
        Assertions.assertThat(found).isPresent();
        Assertions.assertThat(found.get().getId()).isEqualTo(1);
    }

    @Test
    void shouldFindPersonByEmail() {
        // GIVEN
        final PersonDto person = PersonDto.builder().withId(1).withEmail("mira.bellenbaum@gmx.de").build();
        final PersonResource resource = PersonResource.of();

        // WHEN
        resource.save(person);
        final Optional<PersonDto> found = resource.findByEmail("mira.bellenbaum@gmx.de");

        // THEN
        Assertions.assertThat(found).isPresent();
        Assertions.assertThat(found.get().getEmail()).isEqualTo("mira.bellenbaum@gmx.de");
    }

    @Test
    void shouldFindPersonByName() {
        // GIVEN
        final PersonDto person1 = PersonDto.builder().withId(1).withFirstname("Peter").withLastname("Silie").build();
        final PersonDto person2 = PersonDto.builder().withId(2).withFirstname("Pete").withLastname("Mustermann").build();
        final PersonDto person3 = PersonDto.builder().withId(3).withFirstname("Mira").withLastname("Bellenbaum").build();

        final PersonResource resource = PersonResource.of();

        // WHEN
        resource.save(person1);
        resource.save(person2);
        resource.save(person3);
        final List<PersonDto> found = resource.findByName("Pet");

        // THEN
        Assertions.assertThat(found).isInstanceOf(List.class);
        Assertions.assertThat(found.size()).isEqualTo(2);
    }

    @Test
    void shouldFindPersonByFaculty() {
        // GIVEN
        final PersonDto person1 = PersonDto.builder().withId(1).withFacultyId(1).build();
        final PersonDto person2 = PersonDto.builder().withId(2).withFacultyId(2).build();
        final PersonDto person3 = PersonDto.builder().withId(3).withFacultyId(1).build();

        final PersonResource resource = PersonResource.of();

        // WHEN
        resource.save(person1);
        resource.save(person2);
        resource.save(person3);
        final List<PersonDto> found = resource.findByFaculty(2);

        // THEN
        Assertions.assertThat(found).isInstanceOf(List.class);
        Assertions.assertThat(found.size()).isEqualTo(1);
    }
}
