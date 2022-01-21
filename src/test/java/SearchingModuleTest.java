import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchingModuleTest {

    private List<Person> persons;

    @BeforeEach
    void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        persons = objectMapper.readValue(new File("src/test/data/person.json"), new TypeReference<>() {});
    }

    @Test
    void itShouldFindPersonsByID() throws PersonNotFoundException {
        assertEquals(SearchingModule.getPersonByID(persons.toArray(Person[]::new), 2), persons.get(1));
    }

    @Test
    void itShouldThrowExceptionIfIdDoesntExists() {
        assertThrows(PersonNotFoundException.class, () -> SearchingModule.getPersonByID(persons.toArray(Person[]::new), -1));
    }

}
