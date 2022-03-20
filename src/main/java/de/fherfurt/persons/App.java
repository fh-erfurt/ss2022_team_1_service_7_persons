package de.fherfurt.persons;

import java.util.Optional;

import de.fherfurt.persons.models.Person;
import de.fherfurt.persons.modules.PersonBuilder;
import de.fherfurt.persons.validations.PersonFilledValidator;

public class App {
    public static void main(String[] args) {
        PersonBuilder builder = new PersonBuilder();

        /*
         * Optional<Person> person = builder
         * .addValidator(new PersonFilledValidator())
         * .withName("Max", "Mustermann")
         * .build();
         */

        String json = """
                    {
                        "firstname": "Max",
                        "lastname": "Mustermann",
                        "faculty": 3,
                    }
                """;

        Optional<Person> person = builder
                .addValidator(new PersonFilledValidator())
                .fromJSON(json)
                .build();

        System.out.println(".isPresent() = " + person.isPresent());

        if (!person.isPresent()) {
            System.out.println(".getErrors() = " + builder.getErrors().toString());
        } else {
            System.out.println(".get() = " + person.get());
            System.out.println(".get().toJSON = " + person.get().toJSON());
        }

    }
}
