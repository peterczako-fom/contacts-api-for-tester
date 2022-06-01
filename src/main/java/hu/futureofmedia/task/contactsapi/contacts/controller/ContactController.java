package hu.futureofmedia.task.contactsapi.contacts.controller;

import hu.futureofmedia.task.contactsapi.contacts.dtos.ContactCreateAndUpdateDto;
import hu.futureofmedia.task.contactsapi.contacts.dtos.ContactDetailsDto;
import hu.futureofmedia.task.contactsapi.contacts.dtos.ContactDto;
import hu.futureofmedia.task.contactsapi.contacts.dtos.ContactListDto;
import hu.futureofmedia.task.contactsapi.contacts.services.ContactService;
import hu.futureofmedia.task.contactsapi.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(
        path = "/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PreAuthorize("hasAnyAuthority('CREATE_CONTACT')")
    @Operation(
            summary = "Create contact",
            parameters = @Parameter(in = ParameterIn.QUERY, name = "dto", description = "Contact dto",
                    content = @Content(schema = @Schema(implementation = ContactDto.class))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contact created",
                    content = @Content(schema = @Schema(implementation = ContactDto.class))),
            @ApiResponse(responseCode = "400", description = "Input data not appropriate",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorMessage.class)))),
            @ApiResponse(responseCode = "404", description = "Given company id not found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorMessage.class)))) })
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDto createContact(
            @Valid @RequestBody ContactCreateAndUpdateDto command) {
        return contactService.createContact(command);
    }

    @Operation(
            summary = "List contacts from given page",
            parameters = @Parameter(in = ParameterIn.QUERY, name = "page", description = "Searched page (0..N)",
                    content = @Content(schema = @Schema(defaultValue = "0", example = "0"))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contacts listed",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ContactDto.class)))),
            @ApiResponse(responseCode = "400", description = "Given page is negative",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorMessage.class)))) })
    @GetMapping()
    public ContactListDto getAllContacts(
            @RequestParam(value = "page", defaultValue = "0") Integer page) {
        return contactService.getAllContacts(page);
    }

    @Operation(
            summary = "Get contact by id",
            parameters = @Parameter(in = ParameterIn.QUERY, name = "id", description = "Contact id",
                    content = @Content(schema = @Schema(example = "1L"))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact returned",
                    content = @Content(schema = @Schema(implementation = ContactDto.class))),
            @ApiResponse(responseCode = "404", description = "Given id not found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorMessage.class)))) })
    @GetMapping("/{id}")
    public ContactDto getContactById(@PathVariable Long id) {
        return contactService.getContactById(id);
    }

    @Operation(
            summary = "Get contact details by id",
            parameters = @Parameter(in = ParameterIn.QUERY, name = "id", description = "Contact id",
                    content = @Content(schema = @Schema(example = "1L"))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact details returned",
                    content = @Content(schema = @Schema(implementation = ContactDetailsDto.class))),
            @ApiResponse(responseCode = "404", description = "Given id not found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorMessage.class)))) })
    @GetMapping("/{id}/details")
    public ContactDetailsDto getContactDetailsById(@PathVariable Long id) {
        return contactService.getContactDetailsById(id);
    }

    @Operation(
            summary = "Update contact",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "id", description = "Contact id",
                            content = @Content(schema = @Schema(example = "1L"))),
                    @Parameter(in = ParameterIn.QUERY, name = "dto", description = "Contact dto",
                            content = @Content(schema = @Schema(implementation = ContactDto.class)))
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact returned"),
            @ApiResponse(responseCode = "404", description = "Given id not found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorMessage.class)))) })
    @PutMapping("/{id}")
    public ContactDto updateContact(@PathVariable Long id, @RequestBody ContactCreateAndUpdateDto dto) {
        return contactService.updateContact(id, dto);
    }

    @Operation(
            summary = "Delete contact by id",
            parameters = @Parameter(in = ParameterIn.QUERY, name = "id", description = "Contact id",
                    content = @Content(schema = @Schema(example = "1L"))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact deleted"),
            @ApiResponse(responseCode = "404", description = "Given id not found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorMessage.class)))) })
    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
    }

    @Operation(
            summary = "Map of companies")
    @ApiResponse(responseCode = "200", description = "Returned map of companies",
            content = @Content(schema = @Schema(implementation = Map.class)))
    @GetMapping("company-options")
    public Map<Long, String> getCompanyOptions() {
        return contactService.getCompanyOptions();
    }


}
