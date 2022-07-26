package de.fherfurt.person.person.boundary;

import de.fherfurt.person.core.mappers.BeanMapper;
import de.fherfurt.person.person.entity.models.Image;
import de.fherfurt.persons.client.objects.ImageDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
public class ImageMapperTest {

    @Test
    void shouldMapCompleteImageEntityToDto() {
        final byte[] content = new byte[] { 1, 0, 1, 0, 1 };

        // GIVEN
        final Image entity = Image.builder()
                .withName( "Hello World" )
                .withSuffix( "png" )
                .withContent( content )
                .build();

        // WHEN
        final ImageDto result = BeanMapper.mapToDto( entity );

        // THEN
        Assertions.assertEquals( result.getName(), "Hello World" );
        Assertions.assertEquals( result.getSuffix(), "png" );
        Assertions.assertArrayEquals( result.getContent(), content );
    }

    @Test
    void shouldMapCompleteImageDtoToEntity() {
        final byte[] content = new byte[] { 1, 0, 1, 0, 1 };

        // GIVEN
        final ImageDto dto = ImageDto.builder()
                .withName( "Hello World" )
                .withSuffix( "png" )
                .withContent( content )
                .build();

        // WHEN
        final Image result = BeanMapper.mapFromDto( dto );

        // THEN
        Assertions.assertEquals( result.getName(), "Hello World" );
        Assertions.assertEquals( result.getSuffix(), "png" );
        Assertions.assertArrayEquals( result.getContent(), content );
    }

}
