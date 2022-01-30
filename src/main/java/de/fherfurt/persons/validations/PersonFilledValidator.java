package de.fherfurt.persons.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.fherfurt.persons.core.classes.ValidationCondition;
import de.fherfurt.persons.core.classes.ValidationErrorBuilder;
import de.fherfurt.persons.core.errors.ValidationError;
import de.fherfurt.persons.core.interfaces.IValidator;
import de.fherfurt.persons.models.Person;

/**
 * Validator to check if all fields of the person got filled.
 * 
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
public class PersonFilledValidator implements IValidator<Person> {

    @Override
    public Optional<List<ValidationError>> validate(Person person) {
        List<ValidationError> errors = new ArrayList<>();

        new ValidationErrorBuilder<String>()
                .withCondition(new ValidationCondition<String>()
                        .withValue(person.getFirstname())
                        .isRequired("firstname"))
                .withCondition(new ValidationCondition<String>()
                        .withValue(person.getFirstname())
                        .isBetween(2, 30, "firstname"))
                .addTo(errors);

        new ValidationErrorBuilder<String>()
                .withCondition(new ValidationCondition<String>()
                        .withValue(person.getLastname())
                        .isRequired("lastname"))
                .withCondition(new ValidationCondition<String>()
                        .withValue(person.getLastname())
                        .isBetween(2, 30, "lastname"))
                .addTo(errors);

        new ValidationErrorBuilder<String>()
                .withCondition(new ValidationCondition<String>()
                        .withValue(person.getFaculty())
                        .isRequired("faculty"))
                .withCondition(new ValidationCondition<String>()
                        .withValue(person.getFaculty())
                        .isMin(0, "faculty"))
                .addTo(errors);

        return ValidationErrorBuilder.evaluate(errors);
    }
}
