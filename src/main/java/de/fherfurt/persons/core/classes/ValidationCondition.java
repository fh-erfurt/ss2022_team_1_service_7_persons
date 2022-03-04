package de.fherfurt.persons.core.classes;

import java.util.Optional;

import de.fherfurt.persons.core.errors.ValidationError;

public class ValidationCondition<T> {
    private int code = 0;
    private String msg = "";
    private boolean error = false;
    private T value = null;

    /**
     * Adds the error code.
     *
     * @param code The error code.
     * 
     * @return The current builder instance.
     */
    public ValidationCondition<T> withCode(int code) {
        this.code = code;
        return this;
    }

    /**
     * Adds the value to the condition.
     *
     * @param value The value which should gets evalutated.
     * 
     * @return The current builder instance.
     */
    public ValidationCondition<T> withValue(T value) {
        this.value = value;
        return this;
    }

    /**
     * Adds the error message.
     *
     * @param msg The error message.
     * 
     * @return The current builder instance.
     */
    public ValidationCondition<T> withMsg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * Adds the error condition.
     *
     * @param error The error condition.
     * 
     * @return The current builder instance.
     */
    public ValidationCondition<T> checkCondition(boolean error) {
        this.error = error;
        return this;
    }

    /**
     * Checks if the provided value is provided (no defailt values like "" or -1).
     * It also creates a special error message.
     *
     * @param field The name of the checked field.
     * 
     * @return The current builder instance.
     */
    public ValidationCondition<T> isRequired(String field) {
        msg = field + " field must be provided.";

        if (value instanceof Integer) {
            error = value.equals(-1);
        }

        if (value instanceof String) {
            error = value.equals("");
        }

        return this;
    }

    /**
     * Checks if the provided value is between some values. If the value is a
     * string, it checks the length of both strings, else it directly compares the
     * values.
     *
     * @param min   The minumum value/length.
     * @param max   The maximum value/length.
     * @param field The name of the checked field.
     * 
     * @return The current builder instance.
     */
    public ValidationCondition<T> isBetween(Integer min, Integer max, String field) {
        if (value instanceof Integer) {
            msg = field + " must be between " + min + " and " + max + ".";
            error = ((Integer) value).compareTo(min) < 0 || ((Integer) value).compareTo(max) > 0;
        }

        if (value instanceof String) {
            msg = field + " must be between " + min + " and " + max + " characters long.";
            error = ((String) value).length() < min || ((String) value).length() > max;
        }

        return this;
    }

    /**
     * Checks if the provided value is at least some value. If the value is a
     * string, it checks the length of the string, else it directly compares the
     * values.
     *
     * @param min   The minumum value/length.
     * @param field The name of the checked field.
     * 
     * @return The current builder instance.
     */
    public ValidationCondition<T> isMin(Integer min, String field) {

        if (value instanceof Integer) {
            msg = field + " must be greater than " + min + ".";
            error = ((Integer) value).compareTo(min) < 0;
        }

        if (value instanceof String) {
            msg = field + " must be at least " + min + " characters long.";
            error = ((String) value).length() < min;
        }

        return this;
    }

    /**
     * Validates the rrors and returns an optional list of errors which is
     * present, if an error occured, empty instead.
     * 
     * @return An optional list of errors which is present, if an error occured,
     *         empty instead.
     */
    public Optional<ValidationError> evaluate() {
        if (!error) {
            return Optional.empty();
        }

        return Optional.of(new ValidationError(msg, code));
    }

}
