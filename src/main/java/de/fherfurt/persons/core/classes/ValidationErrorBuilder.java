package de.fherfurt.persons.core.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.fherfurt.persons.core.errors.ValidationError;

/**
 * Builder class to create validation errors instances.
 * 
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
public class ValidationErrorBuilder<T> {
    List<ValidationError> errors = new ArrayList<>();

    /**
     * Checks the condition and adds it to the error list, if an
     * validation error occured.
     * 
     * @param condition The condition which should get evaluated.
     * 
     * @return The current builder instance.
     */
    public ValidationErrorBuilder<T> withCondition(ValidationCondition<T> condition) {
        Optional<ValidationError> err = condition.evaluate();

        if (err.isPresent()) {
            errors.add(err.get());
        }

        return this;
    }

    /**
     * Adds all the errors to the existing error list, if an error exists.
     * 
     * @param errors The current error list.
     * 
     * @return The current builder instance.
     */
    public ValidationErrorBuilder<T> addTo(List<ValidationError> errors) {
        errors.addAll(this.errors);
        return this;
    }

    /**
     * Validates the rrors and returns an optional list of errors which is
     * present, if an error occured, empty instead.
     * 
     * @return An optional list of errors which is present, if an error occured,
     *         empty instead.
     */
    public static Optional<List<ValidationError>> evaluate(List<ValidationError> errors) {
        if (errors.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(errors);
    }

}
