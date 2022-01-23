import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SearchingModuleTest {

    private Person[] persons;

    @BeforeEach
    void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        persons = objectMapper.readValue(new File("src/test/data/person.json"), new TypeReference<>() {});
    }

    @Test
    void itShouldFindPersonsByID() throws PersonNotFoundException {
        assertEquals(SearchingModule.getPersonByID(persons, 2), persons[1]);
    }

    @Test
    void itShouldThrowExceptionIfIdDoesntExists() {
        assertThrows(PersonNotFoundException.class, () -> SearchingModule.getPersonByID(persons, -1));
    }

    @Test
    void itShouldFindPersonsByFacultyID() {
        Person[] found = SearchingModule.getPersonsByFacultyID(persons, 1);
        assertArrayEquals(found, new Person[] {persons[0], persons[2]});
    }

    @Test
    void itShouldReturnEmptyArrayIfFacultyPersonsNotFound() {
        Person[] found = SearchingModule.getPersonsByFacultyID(persons, -1);
        assertArrayEquals(found, new Person[] {});
    }

    @Test
    void itShouldFindPersonsByPhrase() {
        Person[] found = SearchingModule.getPersonsByName(persons, "org");
        assertArrayEquals(found, new Person[] {persons[1], persons[2]});
    }

    @Test
    void itShouldReturnEmptyIfNoNameMatches() {
        Person[] found = SearchingModule.getPersonsByName(persons, "zzz");
        assertArrayEquals(found, new Person[] {});
    }

}
