package net.tyt.sample.stream.domain;

import lombok.Builder;
import lombok.Value;

/**
 *
 * @author Igor Tytar
 */

@Value
@Builder
public class Person {
    private String firstName;
    private String lastName;
}
