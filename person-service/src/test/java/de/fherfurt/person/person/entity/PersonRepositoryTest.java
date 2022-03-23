package de.fherfurt.person.person.entity;

import de.fherfurt.person.core.persistence.Database;
import de.fherfurt.person.core.persistence.errors.ToManyResultsException;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static de.fherfurt.person.TestTags.DOMAIN;
import static de.fherfurt.person.TestTags.PERSISTENCE;
import static de.fherfurt.person.TestTags.UNIT;

/**
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@Tags({
        @Tag(UNIT),
        @Tag(DOMAIN),
        @Tag(PERSISTENCE)
})
@ExtendWith(MockitoExtension.class)
public class PersonRepositoryTest {
    @Mock
    Database database;

    @SuppressWarnings("unchecked")
    @Test
    void shouldThrowExceptionIfToManyUsersAreFound() {
        try (MockedStatic<Database> db = Mockito.mockStatic(Database.class)) {
            // GIVEN
            db.when(Database::newInstance).thenReturn(database);

            final PersonRepository repository = PersonRepository.of();

            Mockito
                    .when(database.findBy(Mockito.eq(Person.class), Mockito.any(Predicate.class)))
                    .thenReturn(List.of(Person.builder().withId(1).withEmail("test@gmx.de").build(), Person.builder().withEmail("test@gmx.de").withId(2).build()));

            // WHEN
            final Throwable result = Assertions.catchThrowable(() -> repository.findByEmail("test@gmx.de"));

            // THEN
            Assertions.assertThat(result)
                    .isInstanceOf(ToManyResultsException.class)
                    .hasMessage("No unique result found for email [test@gmx.de]");
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    void shouldReturnFoundEntityByEmail() {
        try (MockedStatic<Database> db = Mockito.mockStatic(Database.class)) {
            // GIVEN
            db.when(Database::newInstance).thenReturn(database);

            final PersonRepository repository = PersonRepository.of();

            Mockito
                    .when(database.findBy(Mockito.eq(Person.class), Mockito.any(Predicate.class)))
                    .thenReturn(List.of(Person.builder().withId(1).withEmail("test@gmx.de").build()));

            // WHEN
            final Optional<Person> result = repository.findByEmail("test@gmx.de");

            // THE
            Assertions.assertThat(result).isPresent();
            Assertions.assertThat(result.get().getEmail()).isEqualTo("test@gmx.de");
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    void shouldReturnFoundEntityByName() {
        try (MockedStatic<Database> db = Mockito.mockStatic(Database.class)) {
            // GIVEN
            db.when(Database::newInstance).thenReturn(database);

            final PersonRepository repository = PersonRepository.of();

            Mockito
                    .when(database.findBy(Mockito.eq(Person.class), Mockito.any(Predicate.class)))
                    .thenReturn(List.of(
                            Person.builder().withId(1).withFirstname("Peter").withLastname("Silie").build(),
                            Person.builder().withId(2).withFirstname("Pete").withLastname("Mustermann").build()
                    ));

            // WHEN
            final List<Person> result = repository.findByName("Pete");

            // THE
            Assertions.assertThat(result).isInstanceOf(List.class);
            Assertions.assertThat(result.size()).isEqualTo(2);
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    void shouldReturnFoundEntityByFaculty() {
        try (MockedStatic<Database> db = Mockito.mockStatic(Database.class)) {
            // GIVEN
            db.when(Database::newInstance).thenReturn(database);

            final PersonRepository repository = PersonRepository.of();

            Mockito
                    .when(database.findBy(Mockito.eq(Person.class), Mockito.any(Predicate.class)))
                    .thenReturn(List.of(Person.builder().withId(1).withFacultyId(2).build()));

            // WHEN
            final List<Person> result = repository.findByFaculty(2);

            // THE
            Assertions.assertThat(result).isInstanceOf(List.class);
            Assertions.assertThat(result.size()).isEqualTo(1);
        }
    }

    @Test
    void shouldCallSaveEntity() {
        try (MockedStatic<Database> db = Mockito.mockStatic(Database.class)) {
            // GIVEN
            db.when(Database::newInstance).thenReturn(database);

            final PersonRepository repository = PersonRepository.of();
            final Person entity = Person.builder().build();

            // WHEN
            repository.save(entity);

            // THE
            Mockito.verify(database, Mockito.times(1)).save(entity);
        }
    }

    @Test
    void shouldCallDeleteEntity() {
        try (MockedStatic<Database> db = Mockito.mockStatic(Database.class)) {
            // GIVEN
            db.when(Database::newInstance).thenReturn(database);

            final PersonRepository repository = PersonRepository.of();
            final Person entity = Person.builder().build();

            // WHEN
            repository.delete(entity);

            // THE
            Mockito.verify(database, Mockito.times(1)).delete(entity);
        }
    }
}
