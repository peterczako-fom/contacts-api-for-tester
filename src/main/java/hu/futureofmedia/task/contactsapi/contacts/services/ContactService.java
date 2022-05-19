package hu.futureofmedia.task.contactsapi.contacts.services;

import hu.futureofmedia.task.contactsapi.contacts.dtos.ContactCreateAndUpdateDto;
import hu.futureofmedia.task.contactsapi.contacts.dtos.ContactDetailsDto;
import hu.futureofmedia.task.contactsapi.contacts.dtos.ContactDto;
import hu.futureofmedia.task.contactsapi.contacts.dtos.ContactListDto;

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
