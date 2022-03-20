package de.fherfurt.person.core.containers;

import lombok.Builder;
import lombok.Getter;

/**
 * This class represents a container for three related values. It extends the {@link Tuple} with an additional value.
 *
 * @param <VALUE_1> Generic type of the value 1
 * @param <VALUE_2> Generic type of the value 2
 * @param <VALUE_3> Generic type of the value 3
 * @author Michael Rhoese <michael.rhoese@fh-erfurt.de>
 */
@Getter
public class Tuple3<VALUE_1, VALUE_2, VALUE_3> extends Tuple<VALUE_1, VALUE_2> {
    private final VALUE_3 v3;

    @Builder(setterPrefix = "with")
    public Tuple3(final VALUE_1 v1, final VALUE_2 v2, final VALUE_3 v3) {
        super(v1, v2);
        this.v3 = v3;
    }
}