package de.fherfurt.person.person.boundary;

import de.fherfurt.persons.client.objects.PersonDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static de.fherfurt.person.TestTags.BOUNDARY;
import static de.fherfurt.person.TestTags.DOMAIN;
import static de.fherfurt.person.TestTags.INTEGRATION;

@Tags({
        @Tag(INTEGRATION),
        @Tag(DOMAIN),
        @Tag(BOUNDARY),
})
public class PersonResourceTest {
    @Test
    void shouldSaveAndFindNewPerson() {
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
        Assertions.assertTrue(found.isPresent());

        System.out.println(found.get());


        Assertions.assertEquals(found.get().getEmail(), "cfaunch0@symantec.com");
    }
}
