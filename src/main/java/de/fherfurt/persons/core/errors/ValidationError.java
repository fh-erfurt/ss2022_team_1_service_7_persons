package de.fherfurt.persons.core.errors;

/**
 * Error class for validation errors.
 * 
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
public class ValidationError {
    private String msg;
    private int code;

    /**
     * Creates an instance of validation error.
     *
     * @param msg  The validation error message.
     * @param code The validation error code.
     */
    public ValidationError(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    /**
     * Returns the error as string representation.
     *
     * @return The string representation of the error: ValidationError (code): msg.
     */
    @Override
    public String toString() {
        return "ValidationError (" + this.code + "): " + this.msg;
    }
}
