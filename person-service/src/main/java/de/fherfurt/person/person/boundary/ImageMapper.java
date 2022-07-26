package de.fherfurt.person.person.boundary;

import de.fherfurt.person.core.mappers.BeanMapper;
import de.fherfurt.person.person.entity.models.Image;
import de.fherfurt.persons.client.objects.ImageDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

/**
 * Mapper that allows the conversion of {@link Image} to/from {@link ImageDto}.
 *
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@Mapper(
        builder = @Builder( disableBuilder = true ),
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        mappingControl = DeepClone.class
)
public interface ImageMapper extends BeanMapper<Image, ImageDto> {
    ImageMapper INSTANCE = Mappers.getMapper( ImageMapper.class );
}
