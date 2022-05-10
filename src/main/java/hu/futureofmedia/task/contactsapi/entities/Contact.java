package hu.futureofmedia.task.contactsapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)

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

    @PrePersist
    private void setCreatedDate() {
        this.createdDate = this.lastModifiedDate = ZonedDateTime.now();
    }

    @PreUpdate
    private void setLastModifiedDate() {
        this.lastModifiedDate = ZonedDateTime.now();
    }

    public enum Status {
        ACTIVE, DELETED
    }
}