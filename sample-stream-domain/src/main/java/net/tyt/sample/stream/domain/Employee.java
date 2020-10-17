package net.tyt.sample.stream.domain;

import java.util.Date;
import lombok.Builder;
import lombok.Value;

/**
 *
 * @author Igor Tytar
 */
@Value
@Builder
public class Employee {
    private Person person;
    private String position;
    private Date startDate;
}
