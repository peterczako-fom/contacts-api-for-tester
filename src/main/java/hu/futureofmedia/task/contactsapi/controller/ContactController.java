package hu.futureofmedia.task.contactsapi.controller;

import hu.futureofmedia.task.contactsapi.dto.ContactCreateDto;
import hu.futureofmedia.task.contactsapi.dto.ContactDto;
import hu.futureofmedia.task.contactsapi.dto.ContactListDto;
import hu.futureofmedia.task.contactsapi.exception.ErrorMessage;
import hu.futureofmedia.task.contactsapi.service.ContactService;
import hu.futureofmedia.task.contactsapi.utility.LoggerHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(
        path = "/api/v1/contacts",
        produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("http://localhost:4200/")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

    private final HttpServletRequest request;


    @Operation(
            summary = "Create contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contact created",
                    content = @Content(schema = @Schema(implementation = ContactDto.class))),
            @ApiResponse(responseCode = "400", description = "Input data not appropriate",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorMessage.class)))),
            @ApiResponse(responseCode = "404", description = "Given company id not found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorMessage.class)))) })
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDto createContact(
            @Valid @RequestBody ContactCreateDto command) {
        LoggerHelper.requestLog(LOGGER, request, command);
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
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ContactListDto getAllContacts(
            @RequestParam(value = "page", defaultValue = "0") Integer page) {
        LoggerHelper.requestLog(LOGGER, request);
        return contactService.getAllContacts(page);
    }

    @GetMapping("company-options")
    public Map<Long, String> getCompanyOptions() {
        return contactService.getCompanyOptions();
    }
}
