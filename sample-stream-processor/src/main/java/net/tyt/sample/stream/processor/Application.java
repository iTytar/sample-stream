package net.tyt.sample.stream.processor;

import java.util.Date;
import java.util.Scanner;
import java.util.function.Function;
import net.tyt.sample.stream.domain.Employee;
import net.tyt.sample.stream.domain.Person;
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
    public Function<Person,Employee> process() {
        return p -> getEmployee(p);
    }
    
    private static Employee getEmployee(final Person person) {
        final String prompt = "Enter department for " +person + " : ";
        final Scanner in = new Scanner(System.in);
        System.out.print(prompt);
        String l = in.nextLine();
        while(l.isBlank()) {
            System.out.println("Incorrect data, try again!");
            System.out.print(prompt);
            l = in.nextLine();
        }
        return Employee.builder()
                .person(person)
                .position(l)
                .startDate(new Date())
                .build(); 
    }
    
}
