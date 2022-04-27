package hu.futureofmedia.task.contactsapi.service;

import hu.futureofmedia.task.contactsapi.dto.ContactCreateDto;
import hu.futureofmedia.task.contactsapi.dto.ContactDto;
import hu.futureofmedia.task.contactsapi.dto.ContactListDto;
import hu.futureofmedia.task.contactsapi.entities.Company;
import hu.futureofmedia.task.contactsapi.entities.Contact;
import hu.futureofmedia.task.contactsapi.property.ContactProperties;
import hu.futureofmedia.task.contactsapi.repositories.ContactRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@EnableConfigurationProperties(ContactProperties.class)
public class ContactService {

    private final ContactRepository contactRepository;

    private final CompanyService companyService;

    private final ContactProperties contactProperties;

    public ContactService(
            ContactRepository contactRepository,
            CompanyService companyService,
            ContactProperties contactProperties) {
        this.contactRepository = contactRepository;
        this.companyService = companyService;
        this.contactProperties = contactProperties;
    }

    public ContactDto createContact(ContactCreateDto command) {
        Contact toSave = mapToContact(command);
        Contact saved = contactRepository.save(toSave);
        return mapToInfo(saved);
    }

    public ContactListDto getAllContacts(Integer page) {
        Pageable pageable = PageRequest.of(page, contactProperties.getPageSize(), Sort.by("firstName", "lastName", "id").descending());
        return new ContactListDto(
                contactRepository.getAllActiveContact(pageable)
        );
    }

    private Contact mapToContact(ContactCreateDto command) {
        return new Contact(
                command.getFirstName().trim(),
                command.getLastName().trim(),
                command.getEmail(),
                command.getPhoneNumber(),
                companyService.findCompanyById(command.getCompanyId()),
                Optional.ofNullable(command.getComment()).map(String::trim).orElse(""),
                Contact.Status.ACTIVE
        );
    }


    private ContactDto mapToInfo(Contact contact) {
        return new ContactDto(
                contact.getId(),
                contact.getFirstName(),
                contact.getLastName(),
                contact.getEmail(),
                contact.getPhoneNumber(),
                contact.getCompany().getName(),
                contact.getComment(),
                contact.getCreatedDate(),
                contact.getLastModifiedDate()
        );
    }

    public Map<Long, String> getCompanyOptions() {
        return companyService.findAll().stream()
                .sorted(Comparator.comparing(Company::getName))
                .collect(Collectors.toMap(Company::getId, Company::getName, (v1, v2) -> v1, LinkedHashMap::new));
    }
}
