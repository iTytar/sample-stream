package net.tyt.sample.stream.consumer;

import java.util.function.Consumer;
import net.tyt.sample.stream.domain.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author Igor Tytar
 */

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Consumer<Employee> consume() {
        return e -> System.out.println("Hired: " + e);
    }
}
