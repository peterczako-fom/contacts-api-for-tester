package hu.futureofmedia.task.contactsapi.contacts.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactListItemDto {

    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String companyName;
}
