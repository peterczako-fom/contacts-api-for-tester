package hu.futureofmedia.task.contactsapi.users.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCreateAndUpdateDto {

    private String email;
    private String password;
}
