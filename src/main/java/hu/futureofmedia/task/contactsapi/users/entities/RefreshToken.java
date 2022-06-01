package hu.futureofmedia.task.contactsapi.users.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class RefreshToken {

    @Id
    @GeneratedValue
    private Long id;
    private String token;
    private boolean active;

    @ManyToOne()
    private AppUser user;
}
