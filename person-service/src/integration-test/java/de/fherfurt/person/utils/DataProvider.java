package de.fherfurt.person.utils;

import de.fherfurt.persons.client.objects.AccountDto;
import de.fherfurt.persons.client.objects.PersonDto;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    private static DataProvider instance;

    public static DataProvider of() {
        if (instance == null) instance = new DataProvider();
        return instance;
    }

    public final static List<PersonDto> personList = new ArrayList<>();

    public DataProvider() {
        final PersonDto person1 = PersonDto.builder()
                .withAccount(AccountDto.builder()
                        .withEmail("cfaunch0@symantec.com")
                        .withPassword("1234")
                        .withUsername("CorissaFaunch42")
                        .build()

                )
                .withSalutation("Frau")
                .withPhone("01575 31834128")
                .withFax("04613 18231247")
                .withTitles(List.of("Dr", "Prof"))
                .withPositions(List.of("Dozentin"))
                .withFirstname("Corissa")
                .withLastname("Faunch")
                .withFacultyId(123)
                .build();

        personList.addAll(List.of(person1));
    }

    public PersonDto person(int index) {
        return personList.get(index - 1);
    }

}
