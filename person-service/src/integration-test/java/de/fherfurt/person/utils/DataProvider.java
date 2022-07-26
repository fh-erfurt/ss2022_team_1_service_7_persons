package de.fherfurt.person.utils;

import de.fherfurt.persons.client.objects.AccountDto;
import de.fherfurt.persons.client.objects.PersonDto;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    private static DataProvider instance;

    public static DataProvider of() {
        if ( instance == null ) instance = new DataProvider();
        return instance;
    }

    public final static List<PersonDto> personList = new ArrayList<>();

    public DataProvider() {
        final PersonDto person1 = PersonDto.builder()
                .withAccount( AccountDto.builder()
                        .withEmail( "cfaunch0@symantec.com" )
                        .withPassword( "1234" )
                        .withUsername( "CorissaFaunch42" )
                        .build()

                )
                .withSalutation( "Frau" )
                .withPhone( "01575 31834128" )
                .withFax( "04613 18231247" )
                .withTitles( List.of( "Dr", "Prof" ) )
                .withPositions( List.of( "Dozentin" ) )
                .withFirstname( "Corissa" )
                .withLastname( "Faunch" )
                .withFacultyId( 123 )
                .build();

        final PersonDto person2 = PersonDto.builder()
                .withAccount( AccountDto.builder()
                        .withEmail( "tobias0@symantec.com" )
                        .withPassword( "123k12iuj4h18924z12jh4esadasd!*##321ß3" )
                        .withUsername( "TobiasKärst69" )
                        .build()

                )
                .withSalutation( "Herr" )
                .withPhone( "01575 31124128" )
                .withFax( "04613 181241247" )
                .withTitles( List.of() )
                .withPositions( List.of( "Studierender" ) )
                .withFirstname( "Tobias" )
                .withLastname( "Kärst" )
                .withFacultyId( 420 )
                .build();

        final PersonDto person3 = PersonDto.builder()
                .withAccount( AccountDto.builder()
                        .withEmail( "tom0@symantec.com" )
                        .withPassword( "123k12iuj4h18924z12jh4esadasd!*##321ß3" )
                        .withUsername( "TomKärst69" )
                        .build()

                )
                .withSalutation( "Herr" )
                .withPhone( "01575 31124128" )
                .withFax( "04613 181241247" )
                .withTitles( List.of() )
                .withPositions( List.of( "Studierender" ) )
                .withFirstname( "Tom" )
                .withLastname( "Kärst" )
                .withFacultyId( 420 )
                .build();

        personList.addAll( List.of( person1, person2, person3 ) );
    }

    public PersonDto person( int index ) {
        return personList.get( index - 1 );
    }

}
