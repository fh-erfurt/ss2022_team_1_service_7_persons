package de.fherfurt.person.person.boundary;

import de.fherfurt.person.core.mappers.BeanMapper;
import de.fherfurt.person.person.entity.Person;
import de.fherfurt.persons.client.objects.PersonDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.fherfurt.person.TestTags.BOUNDARY;
import static de.fherfurt.person.TestTags.DOMAIN;
import static de.fherfurt.person.TestTags.UNIT;

@Tags({
        @Tag(UNIT),
        @Tag(DOMAIN),
        @Tag(BOUNDARY)
})
public class PersonMapperTest {

    @Test
    void shouldMapCompletePersonEntityToDto() {
        // GIVEN
        final Person entity = Person.builder()
                .withId(1)
                .withEmail("cdredge0@hhs.gov")
                .withSalutation("Ms.")
                .withFirstname("Carline")
                .withLastname("Dredge")
                .withUsername("CarlineDredge")
                .withPhone("412-893-9724")
                .withImageUrl("https://placehold.jp/150x150.png")
                .withFax("963-270-1871")
                .withFacultyId(3)
                .withTitles(List.of("Dr."))
                .withPositions(List.of("Lecturer"))
                .build();

        // WHEN
        final PersonDto result = BeanMapper.mapToDto(entity);

        // THEN
        Assertions.assertEquals(result.getId(), 1);
        Assertions.assertEquals(result.getEmail(), "cdredge0@hhs.gov");
        Assertions.assertEquals(result.getUsername(), "CarlineDredge");
    }

    @Test
    void shouldMapCompletePersonDtoToEntity() {
        // GIVEN
        final PersonDto dto = PersonDto.builder()
                .withId(1)
                .withEmail("cdredge0@hhs.gov")
                .withSalutation("Ms.")
                .withFirstname("Carline")
                .withLastname("Dredge")
                .withUsername("CarlineDredge")
                .withPhone("412-893-9724")
                .withImageUrl("https://placehold.jp/150x150.png")
                .withFax("963-270-1871")
                .withFacultyId(3)
                .withTitles(List.of("Dr."))
                .withPositions(List.of("Lecturer"))
                .build();

        // WHEN
        final Person result = BeanMapper.mapFromDto(dto);

        // THEN
        Assertions.assertEquals(result.getId(), 1);
        Assertions.assertEquals(result.getEmail(), "cdredge0@hhs.gov");
        Assertions.assertEquals(result.getUsername(), "CarlineDredge");
    }

}
