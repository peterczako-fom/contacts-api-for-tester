package hu.futureofmedia.task.contactsapi.dto;

import hu.futureofmedia.task.contactsapi.validation.EmailBannedDomain;
import hu.futureofmedia.task.contactsapi.validation.PhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactCreateAndUpdateDto {

    @Schema(example = "John")
    @NotBlank
    private String firstName;

    @Schema(example = "Doe")
    @NotBlank
    private String lastName;

    @Schema(example = "john.doe@gmail.com")
    @NotEmpty
    @Email
    @EmailBannedDomain
    private String email;

    @Schema(example = "+36201234567")
    @PhoneNumber
    private String phoneNumber;

    @Schema(example = "1")
    @NotNull
    @Positive
    private Long companyId;

    @Schema(example = "Some comment for contact")
    @NotNull
    private String comment;

}
