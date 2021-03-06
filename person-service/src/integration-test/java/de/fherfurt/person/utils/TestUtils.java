package de.fherfurt.person.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestUtils {

    public static byte[] loadImage( final String fileName ) {
        try {
            File file = new File( "src/integration-test/resources/images/" + fileName );
            FileInputStream fl = new FileInputStream( file );
            byte[] arr = new byte[ ( int ) file.length() ];
            int bytesRead = fl.read( arr );
            fl.close();
            return arr;
        } catch ( IOException e ) {
            throw new RuntimeException( "Could not load file '" + fileName + "' from 'resources/images", e );
        }
    }

}
