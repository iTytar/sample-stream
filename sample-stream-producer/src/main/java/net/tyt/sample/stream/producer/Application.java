package net.tyt.sample.stream.producer;

import net.tyt.sample.stream.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Igor Tytar
 */
@SpringBootApplication
@Controller
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private StreamBridge streamBridge;

    @PostMapping("/api/persons")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void produce(@RequestBody Person person) {
        System.out.println("produce: " + person);
        streamBridge.send("produce-out-0", person);
    }

    @GetMapping("/api/persons")
    public @ResponseBody Person produce() {
        Person person = Person.builder()
                .firstName("Igor")
                .lastName("Tytar")
                .build();
        System.out.println("Person: " + person);
        return person;
    }

}
