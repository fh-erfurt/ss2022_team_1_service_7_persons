package de.fherfurt.person.core.mappers;

import de.fherfurt.person.core.mappers.BeanMapperUtils.MapperTargets;
import de.fherfurt.person.core.persistence.BaseBusinessEntity;
import org.mapstruct.InheritInverseConfiguration;

/**
 * This interface represents an API definition for bean mappers and a mapper facade at the same time.
 * The direct usage of the mapper framework isn't a good idea. Sometimes, the performance of frameworks
 * change and the switch to another makes sense.
 *
 * @param <ENTITY> Generic type of entity
 * @param <DTO>    Generic type of DTO
 * @author Michael Rhoese <michael.rhoese@fh-erfurt.de>
 */
public interface BeanMapper<ENTITY extends BaseBusinessEntity, DTO> {

    /**
     * Maps the given entity to its corresponding DTO.
     *
     * @param entity Entity to map
     * @return Resulting DTO of the mapping
     */
    DTO toDto( final ENTITY entity );

    /**
     * Maps the given DTO to its underlying entity.
     *
     * @param dto DTO to map
     * @return Resulting entity of the DTO
     */
    @InheritInverseConfiguration
    ENTITY fromDto( final DTO dto );

    /**
     * Deeply clones the entity.
     *
     * @param toClone Entity to clone
     * @return Cloned entity
     */
    ENTITY clone( ENTITY toClone );

    /**
     * Deeply clones the DTO.
     *
     * @param toClone DTO to clone
     * @return Cloned DTO
     */
    DTO clone( DTO toClone );

    @Ignore
    static <ENTITY extends BaseBusinessEntity, DTO> DTO mapToDto( final ENTITY entity ) {
        final BeanMapper<ENTITY, DTO> beanMapper = BeanMapperUtils.getMapperBy( entity.getClass(), MapperTargets.ENTITY );
        return beanMapper.toDto( entity );
    }

    @Ignore
    static <ENTITY extends BaseBusinessEntity, DTO> ENTITY mapFromDto( final DTO dto ) {
        final BeanMapper<ENTITY, DTO> beanMapper = BeanMapperUtils.getMapperBy( dto.getClass(), MapperTargets.DTO );
        return beanMapper.fromDto( dto );
    }
}