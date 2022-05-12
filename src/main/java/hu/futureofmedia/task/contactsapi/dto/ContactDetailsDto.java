package hu.futureofmedia.task.contactsapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactDetailsDto {

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

    @Schema()
    private ZonedDateTime createdDate;

    @Schema()
    private ZonedDateTime lastModifiedDate;
}
