package de.fherfurt.person.core.containers;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This class represents a container to store two related values in one object. Its mainly used in collections
 * and for stream operations.
 *
 * @param <VALUE_1> Generic type of the value 1
 * @param <VALUE_2> Generic type of the value 2
 * @author Michael Rhoese <michael.rhoese@fh-erfurt.de>
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED, onConstructor = @__({@Builder(setterPrefix = "with")}))
public class Tuple<VALUE_1, VALUE_2> {
    private final VALUE_1 v1;
    private final VALUE_2 v2;
}
