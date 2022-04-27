package hu.futureofmedia.task.contactsapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactDto {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "John")
    private String firstName;

    @Schema(example = "Doe")
    private String lastName;

    @Schema(example = "john.doe@gmail.com")
    private String email;

    @Schema(example = "36201234567")
    private String phoneNumber;

    @Schema(example = "Company #1")
    private String companyName;

    @Schema(example = "Some comment for contact")
    private String comment;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDto that = (ContactDto) o;
        return id.equals(that.id) && firstName.equals(that.firstName) && lastName.equals(that.lastName) && email.equals(that.email) && Objects.equals(phoneNumber, that.phoneNumber) && companyName.equals(that.companyName) && comment.equals(that.comment) && createdDate.equals(that.createdDate) && lastUpdatedDate.equals(that.lastUpdatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phoneNumber, companyName, comment, createdDate, lastUpdatedDate);
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                ", comment='" + comment + '\'' +
                ", createdDate=" + createdDate +
                ", lastUpdatedDate=" + lastUpdatedDate +
                '}';
    }
}
