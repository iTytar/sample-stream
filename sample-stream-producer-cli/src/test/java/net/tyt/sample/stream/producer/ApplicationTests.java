package net.tyt.sample.stream.producer;

import java.util.function.Supplier;
import net.tyt.sample.stream.domain.Person;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.CompositeMessageConverter;

/**
 *
 * @author Igor Tytar
 */

@SpringBootTest
@Import({TestChannelBinderConfiguration.class})
class ApplicationTests {

    @Autowired
    private CompositeMessageConverter messageConverter;

    @Autowired
    private OutputDestination output;

    @Test
    public void testProduce() {
        Person expected = Person.builder()
                .firstName("Test")
                .lastName("Test")
                .build();
        Message m = output.receive();
        System.out.println("Received: " + m.getPayload());
        Object result = messageConverter.fromMessage(m, Person.class);
        System.out.println("Received: " + result);
        assertThat(result).isEqualTo(expected);
    }

    @TestConfiguration
    public static class TestConfig {

        @Bean
        public Supplier<Person> produceTest() {
            return () -> Person.builder()
                    .firstName("Test")
                    .lastName("Test")
                    .build();
        }
    }
}
