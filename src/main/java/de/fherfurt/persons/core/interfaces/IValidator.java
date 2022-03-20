package de.fherfurt.persons.core.interfaces;

import java.util.List;
import java.util.Optional;

import de.fherfurt.persons.core.errors.ValidationError;

/**
 * Interface to create custom validation rules for builders.
 * 
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
public interface IValidator<T> {
    /**
     * Validates the data model and returns an optional list of errors which is
     * present, if an error occured, empty instead.
     * 
     * @param model The model object which should gets evaluated.
     * @return An optional list of errors which is present, if an error occured,
     *         empty instead.
     */
    Optional<List<ValidationError>> validate(T model);
}
