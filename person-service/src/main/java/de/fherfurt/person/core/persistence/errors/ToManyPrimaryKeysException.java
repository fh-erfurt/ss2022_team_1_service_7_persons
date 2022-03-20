package de.fherfurt.person.core.persistence.errors;

import lombok.RequiredArgsConstructor;

import java.util.Objects;

/**
 * Thrown if an entity has multiple fields that are annotated with{@link de.fherfurt.mensa.core.persistence.Id}.
 *
 * @author Michael Rhoese <michael.rhoese@fh-erfurt.de>
 */
@RequiredArgsConstructor
public class ToManyPrimaryKeysException extends RuntimeException {
    private final String type;

    private ToManyPrimaryKeysException(String message, String type) {
        super(message);
        this.type = type;
    }

    public static ToManyPrimaryKeysException of(Class<?> clazz) {
        return of(clazz, null);
    }

    public static ToManyPrimaryKeysException of(Class<?> clazz, String message) {
        return new ToManyPrimaryKeysException(message, clazz.getSimpleName());
    }

    @Override
    public String getLocalizedMessage() {
        return "More then one primary key found on type '" + type + "'." + (Objects.isNull(getMessage()) ? "" : " " + getMessage());
    }
}
