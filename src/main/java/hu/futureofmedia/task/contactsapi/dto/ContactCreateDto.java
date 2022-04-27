package hu.futureofmedia.task.contactsapi.dto;

import hu.futureofmedia.task.contactsapi.validation.EmailBannedDomain;
import hu.futureofmedia.task.contactsapi.validation.PhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactCreateDto {

    @Schema(example = "John")
    @NotBlank
    private String firstName;

    @Schema(example = "Doe")
    @NotBlank
    private String lastName;

    @Schema(example = "john.doe@gmail.com")
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
    private String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactCreateDto that = (ContactCreateDto) o;
        return firstName.equals(that.firstName) && lastName.equals(that.lastName) && email.equals(that.email) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(companyId, that.companyId) && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, phoneNumber, companyId, comment);
    }

    @Override
    public String toString() {
        return "ContactCreateCommand{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phoneNumber + '\'' +
                ", companyId=" + companyId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
