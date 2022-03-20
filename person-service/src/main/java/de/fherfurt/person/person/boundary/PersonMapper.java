package de.fherfurt.person.person.boundary;

import de.fherfurt.person.core.mappers.BeanMapper;
import de.fherfurt.person.person.entity.Person;
import de.fherfurt.persons.client.objects.PersonDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

/**
 * Mapper that allows the conversion of {@link Person} to/from
 * {@link PersonDto}.
 * 
 * @author Jonas Liehmann <johann.liehmann@fh-erfurt.de>
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@Mapper(builder = @Builder(disableBuilder = true), mappingControl = DeepClone.class)
public interface PersonMapper extends BeanMapper<Person, PersonDto> {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
}
