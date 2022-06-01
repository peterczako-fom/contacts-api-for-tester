package hu.futureofmedia.task.contactsapi;

import hu.futureofmedia.task.contactsapi.graphqlqueryresolver.GraphqlConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(GraphqlConfiguration.class)
public class ContactsApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContactsApiApplication.class, args);
    }
}
