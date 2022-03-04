package de.fherfurt.persons.core.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.fherfurt.persons.core.errors.ValidationError;
import de.fherfurt.persons.core.interfaces.IValidator;

/**
 * This wrapper class enables builder classes to add object validators
 * and evaluates them after building.
 * 
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@SuppressWarnings("unchecked")
public abstract class CheckedBuilder<B, T> {
    private List<IValidator<T>> validatorList = new ArrayList<>();
    private List<ValidationError> errorList = new ArrayList<>();

    /**
     * Adds a validation to the validator list.
     *
     * @param validator Validator object which should get added to the list.
     */
    public B addValidator(IValidator<T> validator) {
        validatorList.add(validator);
        return (B) this;
    }

    /**
     * Builds the data holder object and evaluates it. It returnes an instance
     * from Optional, which is only present, if all validations succeeded.
     * 
     * @return The data model instance in an optional wrapper if all validations
     *         succeeded, empty optional instead.
     */
    public Optional<T> build() {
        return checkAndGet(this.instantiate());
    }

    /**
     * Get a list of all occured errors while validating the object.
     * 
     * @return A List of validation errors.
     */
    public List<ValidationError> getErrors() {
        return errorList;
    }

    /**
     * Checks if validation errors occured. If so, it saves them into the
     * error list and returns true, false instead.
     * 
     * @param err Optional list of validation errors.
     * @return True if error(s) occured, false instead.
     */
    private boolean gotError(Optional<List<ValidationError>> err) {
        if (err.isPresent()) {
            errorList.addAll(err.get());
            return true;
        }

        return false;
    }

    /**
     * Checks the data model for all added validations and returns is inside
     * an optional, if the validations succeeded, empty if they failed.
     * 
     * @param value The data model which should get validated.
     * @return The data model instance inside an optional if all validations
     *         succeeded, empty optional if they failed.
     */
    protected Optional<T> checkAndGet(T value) {
        return validatorList.stream()
                // Filter for every validator which returned false
                .filter(v -> gotError(v.validate(value)))
                // Map every false validation for empty object
                .map(v -> (Optional<T>) Optional.empty())
                // If no empty object exists, all validations were
                // successfull, return the object
                .findFirst()
                .orElse(Optional.of(value));
    }

    /**
     * Instantiates the data model and returns it.
     * 
     * @return The instentiated data model.
     */
    protected abstract T instantiate();
}
