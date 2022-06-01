package hu.futureofmedia.task.contactsapi.users.dtos;

import hu.futureofmedia.task.contactsapi.roles.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCreateAndUpdateDto {

    private String email;
    private String password;
    private List<Role> roles;
}
