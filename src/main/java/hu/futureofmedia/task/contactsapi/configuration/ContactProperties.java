package hu.futureofmedia.task.contactsapi.configuration;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ContactProperties {

    @Value("${contact.page-size}")
    private int pageSize = 10;

}
