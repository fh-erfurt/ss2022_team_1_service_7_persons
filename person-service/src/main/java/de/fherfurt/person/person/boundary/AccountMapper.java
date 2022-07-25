package de.fherfurt.person.person.boundary;

import de.fherfurt.person.core.mappers.BeanMapper;
import de.fherfurt.person.person.entity.models.Account;
import de.fherfurt.persons.client.objects.AccountDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

/**
 * Mapper that allows the conversion of {@link Account} to/from {@link AccountDto}.
 *
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@Mapper(
        builder = @Builder(disableBuilder = true),
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        mappingControl = DeepClone.class
)
public interface AccountMapper extends BeanMapper<Account, AccountDto> {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
}
