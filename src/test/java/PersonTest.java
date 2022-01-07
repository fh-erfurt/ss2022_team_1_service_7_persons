import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

class PersonTest {

    private List<Person> persons;

    @BeforeEach
    void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        persons = objectMapper.readValue(new File("src/test/data/person.json"), new TypeReference<>() {});
    }

    @Test
    void itShouldPrintFullName() {
        assertEquals("Bail Haggerwood", persons.get(0).toString());
    }

}
