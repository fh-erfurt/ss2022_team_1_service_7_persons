package de.fherfurt.person.person.boundary;

import de.fherfurt.person.core.mappers.BeanMapper;
import de.fherfurt.person.person.entity.models.Account;
import de.fherfurt.persons.client.objects.AccountDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
public class AccountMapperTest {

    @Test
    void shouldMapCompleteAccountEntityToDto() {
        // GIVEN
        final Account entity = Account.builder()
                    .withEmail( "cfaunch0@symantec.com" )
                    .withPassword( "1234" )
                    .withUsername( "CorissaMustermann42" )
                    .build();

        // WHEN
        final AccountDto result = BeanMapper.mapToDto( entity );

        // THEN
        Assertions.assertEquals( result.getEmail(), "cfaunch0@symantec.com" );
        Assertions.assertEquals( result.getPassword(), "1234" );
        Assertions.assertEquals( result.getUsername(), "CorissaMustermann42" );
    }

    @Test
    void shouldMapCompleteAccountDtoToEntity() {
        // GIVEN
        final AccountDto dto = AccountDto.builder()
                .withEmail( "cfaunch0@symantec.com" )
                .withPassword( "1234" )
                .withUsername( "CorissaMustermann42" )
                .build();

        // WHEN
        final Account result = BeanMapper.mapFromDto( dto );

        // THEN
        Assertions.assertEquals( result.getEmail(), "cfaunch0@symantec.com" );
        Assertions.assertEquals( result.getPassword(), "1234" );
        Assertions.assertEquals( result.getUsername(), "CorissaMustermann42" );
    }

}
