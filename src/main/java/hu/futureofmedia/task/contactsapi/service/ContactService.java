package hu.futureofmedia.task.contactsapi.service;

import hu.futureofmedia.task.contactsapi.dto.ContactCreateAndUpdateDto;
import hu.futureofmedia.task.contactsapi.dto.ContactDetailsDto;
import hu.futureofmedia.task.contactsapi.dto.ContactDto;
import hu.futureofmedia.task.contactsapi.dto.ContactListDto;

import java.util.Map;

public interface ContactService {

    ContactDto createContact(ContactCreateAndUpdateDto command);

    ContactListDto getAllContacts(Integer page);

    ContactDto getContactById(Long id);

    ContactDetailsDto getContactDetailsById(Long id);

    ContactDto updateContact(Long id, ContactCreateAndUpdateDto dto);

    void deleteContact(Long id);

    Map<Long, String> getCompanyOptions();

}
