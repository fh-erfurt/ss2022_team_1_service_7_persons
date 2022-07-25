package de.fherfurt.person.boundary;

import de.fherfurt.person.person.boundary.PersonResource;
import de.fherfurt.person.utils.DataProvider;
import de.fherfurt.persons.client.objects.PersonDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

public class PersonResourceTest {
    @Test
    void shouldSaveNewPerson() {
        // GIVEN
        final PersonResource resource = PersonResource.of();
        final PersonDto person = DataProvider.of().person(1);

        // WHEN
        resource.save(person);
        final PersonDto found = resource.findById(1);

        // THEN
        final PersonDto updatedPerson = person.toBuilder()
                .withAccount(person.getAccount().toBuilder().withId(2).build())
                .withId(1)
                .build();

        Assertions.assertThat(found).isEqualTo(updatedPerson);
    }

    @Test
    void itShouldUpdatePersonIfExists() {
        // GIVEN
        final PersonResource resource = PersonResource.of();
        final PersonDto person = DataProvider.of().person(1);

        // WHEN
        resource.save(person);
        final PersonDto savedPerson = resource.findById(1);

        // THEN
        Assertions.assertThat(savedPerson).isNotNull();

        System.out.println("SAVED");

        // GIVEN
        final PersonDto updatedPerson = savedPerson.toBuilder()
                .withLastname("Mustermann")
                .build();

        // WHEN
        resource.save(updatedPerson);
        final PersonDto found = resource.findById(1);

        System.out.println(resource.findAll());

        // THEN
        Assertions.assertThat(found.getLastname()).isEqualTo("Mustermann");
    }
}
