package hu.futureofmedia.task.contactsapi.service;

import hu.futureofmedia.task.contactsapi.dto.ContactCreateAndUpdateDto;
import hu.futureofmedia.task.contactsapi.dto.ContactDetailsDto;
import hu.futureofmedia.task.contactsapi.dto.ContactDto;
import hu.futureofmedia.task.contactsapi.dto.ContactListDto;
import hu.futureofmedia.task.contactsapi.entities.Company;
import hu.futureofmedia.task.contactsapi.entities.Contact;
import hu.futureofmedia.task.contactsapi.configuration.ContactProperties;
import hu.futureofmedia.task.contactsapi.exception.IdNotFoundException;
import hu.futureofmedia.task.contactsapi.mapper.ContactMapper;
import hu.futureofmedia.task.contactsapi.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    private final CompanyService companyService;

    private final ContactProperties contactProperties;

    private final ContactMapper contactMapper;

    public ContactDto createContact(ContactCreateAndUpdateDto dto) {
        Company company = companyService.findCompanyById(dto.getCompanyId());
        Contact toSave = contactMapper.dtoToEntityForCreate(dto);

        toSave.setCompany(company);
        toSave.setStatus(Contact.Status.ACTIVE);
        Contact saved = contactRepository.save(toSave);
        return contactMapper.entityToDto(saved);
    }

    public ContactListDto getAllContacts(Integer page) {
        Pageable pageable = PageRequest.of(page, contactProperties.getPageSize(), Sort.by("firstName", "lastName", "id").ascending());

        return new ContactListDto(
                contactRepository.getAllContactByStatus(pageable, Contact.Status.ACTIVE)
                        .map(contactMapper::entityToListItemDto)
        );
    }

    public ContactDto getContactById(Long id) {
        Contact entity = getContactByIdOrThrowException(id);
        return contactMapper.entityToDto(entity);
    }

    @Override
    public ContactDetailsDto getContactDetailsById(Long id) {
        Contact entity = getContactByIdOrThrowException(id);
        return contactMapper.entityToDetailsDto(entity);
    }

    public ContactDto updateContact(Long id, ContactCreateAndUpdateDto dto) {
        Company company = companyService.findCompanyById(dto.getCompanyId());
        Contact saved = getContactByIdOrThrowException(id);
        contactMapper.updateContact(saved, dto);
        saved.setCompany(company);
        Contact updated = contactRepository.save(saved);
        return contactMapper.entityToDto(updated);
    }

    public void deleteContact(Long id) {
        contactRepository.updateStatusById(id, Contact.Status.DELETED);
    }

    public Map<Long, String> getCompanyOptions() {
        return companyService.findAll().stream()
                .sorted(Comparator.comparing(Company::getName))
                .collect(Collectors.toMap(Company::getId, Company::getName, (v1, v2) -> v1, LinkedHashMap::new));
    }

    private Contact getContactByIdOrThrowException(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id, Contact.class));
    }
}
