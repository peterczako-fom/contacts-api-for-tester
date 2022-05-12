package hu.futureofmedia.task.contactsapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Schema(example = "1")
    private Long companyId;

    @Schema(example = "Some comment for contact")
    private String comment;

}
