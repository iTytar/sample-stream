package net.tyt.sample.stream.consumer;

import java.util.Date;
import net.tyt.sample.stream.domain.Employee;
import net.tyt.sample.stream.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.GenericMessage;

/**
 *
 * @author Igor Tytar
 */

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
class ApplicationTests {

    @Autowired
    private InputDestination input;

    @Test
    public void testEmptyConfiguration() {
        Employee expected = Employee.builder()
                .person(Person.builder()
                        .firstName("Test")
                        .lastName("Test")
                        .build())
                .position("developer")
                .startDate(new Date())
                .build();
        input.send(new GenericMessage<>(expected));
    }
}
