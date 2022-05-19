package hu.futureofmedia.task.contactsapi.contacts.entities;

import hu.futureofmedia.task.contactsapi.companies.entities.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.MessageFormat;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @ManyToOne
    private Company company;

    private String comment;

    @Enumerated(EnumType.STRING)
    private Status status;

    private ZonedDateTime createdDate;

    private ZonedDateTime lastModifiedDate;

    public String getFullName() {
        return MessageFormat.format("{0} {1}", this.firstName, this.lastName);
    }

    @PrePersist
    private void setCreatedDate() {
        this.createdDate = ZonedDateTime.now();
        this.lastModifiedDate = this.createdDate;
    }

    @PreUpdate
    private void setLastModifiedDate() {
        this.lastModifiedDate = ZonedDateTime.now();
    }

    public enum Status {
        ACTIVE, DELETED
    }
}