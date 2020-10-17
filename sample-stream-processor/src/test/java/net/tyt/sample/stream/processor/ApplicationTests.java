package net.tyt.sample.stream.processor;

import java.util.function.Function;
import net.tyt.sample.stream.domain.Employee;
import net.tyt.sample.stream.domain.Person;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.converter.CompositeMessageConverter;
import org.springframework.messaging.support.GenericMessage;

/**
 *
 * @author Igor Tytar
 */

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
class ApplicationTests {

    @Autowired
    private CompositeMessageConverter messageConverter;

    @Autowired
    private InputDestination input;

    @Autowired
    private OutputDestination output;

    @Test
    public void testEmptyConfiguration() {
        Person person = Person.builder()
                .firstName("Test")
                .lastName("Test")
                .build();
        Employee expected = Employee.builder()
                .person(person)
                .position("developer")
                .build();
        this.input.send(new GenericMessage<>(person));
        Object o = messageConverter.fromMessage(output.receive(), Employee.class);
        System.out.println("Received: " + o);
        assertThat(o).isEqualTo(expected);
    }

    @TestConfiguration
    public static class TestConfig {

        @Bean
        public Function<Person, Employee> processTest() {
            return p -> Employee.builder()
                    .person(p)
                    .position("developer")
                    .build();
        }

    }

}
