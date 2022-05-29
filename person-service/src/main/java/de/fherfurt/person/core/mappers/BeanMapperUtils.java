package de.fherfurt.person.core.mappers;

import de.fherfurt.person.core.containers.Tuple3;
import de.fherfurt.person.core.persistence.BaseBusinessEntity;
import de.fherfurt.person.person.entity.models.Person;
import de.fherfurt.persons.client.objects.PersonDto;
import de.fherfurt.person.person.boundary.PersonMapper;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Utils class with methods and data that are required to work with bean mapper. Instances of mappers have to registered
 * in the MAPPERS list of that class.
 *
 * @author Michael Rhoese <michael.rhoese@fh-erfurt.de>
 */
public class BeanMapperUtils {

    /**
     * Searches a mapper instance by the given type and the Tuple3 supplier. The mappers must be pre-defined in the list of that utils class.
     * If no mapper is available for the type, an exception will be thrown.
     *
     * @param type   Type of the object
     * @param target Target to find the right mapper
     * @param <E>    Generic type of the entity
     * @param <D>    Generic type of the DTO
     * @return Instance of the mapper. Otherwise, an exception will be thrown.
     */
    @SuppressWarnings("unchecked")
    static <E extends BaseBusinessEntity, D> BeanMapper<E, D> getMapperBy(final Class<?> type, final MapperTargets target) {
        final Function<Tuple3<Class<?>, Class<?>, ? extends BeanMapper<?, ?>>, Class<?>> typeSupplier = MapperTargets.ENTITY.equals(target) ? Tuple3::getV1 : Tuple3::getV2;

        return MAPPERS.stream()
                .filter(mapper -> Objects.equals(type, typeSupplier.apply(mapper)))
                .findFirst()
                .map(mapper -> (BeanMapper<E, D>) mapper.getV3())
                .orElseThrow(() -> new NullPointerException("Could not find mapper for " + target.name().toLowerCase() + " type '" + type.getSimpleName() + "'."));
    }

    /**
     * List that holds all available mappers.
     */
    private static final List<Tuple3<Class<?>, Class<?>, ? extends BeanMapper<?, ?>>> MAPPERS = List.of(
            Tuple3.<Class<?>, Class<?>, BeanMapper<?, ?>>builder()
                    .withV1(Person.class)
                    .withV2(PersonDto.class)
                    .withV3(PersonMapper.INSTANCE)
                    .build()
    );

    /**
     * @author Michael Rhoese <michael.rhoese@fh-erfurt.de>
     */
    enum MapperTargets {
        ENTITY, DTO
    }
}