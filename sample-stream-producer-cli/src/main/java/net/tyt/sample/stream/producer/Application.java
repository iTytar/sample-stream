package net.tyt.sample.stream.producer;

import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Stream;
import net.tyt.sample.stream.domain.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

/**
 *
 * @author Igor Tytar
 */

@SpringBootApplication
public class Application {

    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Supplier<Flux<Person>> produce() {
        return () -> Flux.fromStream(Stream.generate(()->getPerson()));
    }

    private static Person getPerson() {
        final String prompt = "Enter person <FirstName>,<LastName> : ";
        System.out.print(prompt);
        String[] cs = in.nextLine().concat(",").split(",", -1);
        while(cs.length < 2 || cs[0].isBlank() || cs[1].isBlank()) {
            System.out.println("Incorrect data, try again!");
            System.out.print(prompt);
            cs = in.nextLine().concat(",").split(",", -1);
        }
        return Person.builder()
                .firstName(cs[0])
                .lastName(cs[1])
                .build();
    }
}
