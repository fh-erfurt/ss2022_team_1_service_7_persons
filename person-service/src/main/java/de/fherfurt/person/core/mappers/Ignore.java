package de.fherfurt.person.core.mappers;

import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@link BeanMapper} interface defines the signatures that are used to generate the mapping logic by Mapstruct.
 * Additionally, there are static methods that are used to mask the finding of required mappers in case of manual
 * calls.These methods must be ignored from code generation. This annotation does that.
 *
 * @author Michael Rhoese <michael.rhoese@fh-erfurt.de>
 */
@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface Ignore { }