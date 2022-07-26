package de.fherfurt.person.core.exceptions;

import java.util.function.Consumer;

/**
 * This functional interface is used to wrap {@link java.util.function.Consumer} calls that throws checked exceptions.
 *
 * @param <PARAM>     Generic type of the parameter
 * @param <EXCEPTION> Generic exception type
 *
 * @author Michael Rhoese <michael.rhoese@fh-erfurt.de>
 */
@FunctionalInterface
public interface ConsumerWithException<PARAM, EXCEPTION extends Exception> {
    void accept( PARAM value ) throws EXCEPTION;

    static <PARAM, EXCEPTION extends Exception> Consumer<PARAM> wrap( ConsumerWithException<PARAM, EXCEPTION> consumer ) {
        return arg -> {
            try {
                consumer.accept( arg );
            } catch ( Exception e ) {
                throw new RuntimeException( e) ;
            }
        };
    }
}
