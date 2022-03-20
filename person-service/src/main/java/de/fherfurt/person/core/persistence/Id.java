package de.fherfurt.person.core.persistence;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is a marker for fields that have to be used as primary key fields. Each entity requires exactly one
 * annotated field.
 *
 * @author Michael Rhoese <michael.rhoese@fh-erfurt.de>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id { }