package de.fherfurt.person.person.business;

import de.fherfurt.person.core.persistence.DataController;
import de.fherfurt.person.person.entity.PersonDao;
import de.fherfurt.person.person.entity.PersonRepository;
import de.fherfurt.person.person.entity.models.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@ExtendWith(MockitoExtension.class)
public class PersonBFTest {

    @Mock
    PersonRepository personRepository;

    @Mock
    FilesBF filesBF;

    @Test
    void shouldCallSaveOnPersonRepository() {
        prepareTest((dataControllerMock, filesBfMock) -> {
            // GIVEN
            PersonBF facade = PersonBF.of();
            final Person entity = Person.builder().build();

            // WHEN
            facade.save(entity);

            // THEN
            Mockito.verify(personRepository, Mockito.times(1)).save(entity);
        });
    }

    private void prepareTest( BiConsumer<MockedStatic<DataController>, MockedStatic<FilesBF>> testCase ) {
        try ( MockedStatic<DataController> personRepo = Mockito.mockStatic( DataController.class ) ) {
            personRepo.when( DataController::getPersonRepository ).thenReturn( personRepository );

            try (MockedStatic<FilesBF> filesFacade = Mockito.mockStatic(FilesBF.class)) {
                filesFacade.when(FilesBF::of).thenReturn(filesBF);
                testCase.accept(personRepo, filesFacade);
            }
        }
    }

}
