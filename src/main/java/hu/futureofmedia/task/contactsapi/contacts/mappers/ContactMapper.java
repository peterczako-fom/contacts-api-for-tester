package hu.futureofmedia.task.contactsapi.contacts.mappers;

import hu.futureofmedia.task.contactsapi.contacts.dtos.ContactCreateAndUpdateDto;
import hu.futureofmedia.task.contactsapi.contacts.dtos.ContactDetailsDto;
import hu.futureofmedia.task.contactsapi.contacts.dtos.ContactDto;
import hu.futureofmedia.task.contactsapi.contacts.dtos.ContactListItemDto;
import hu.futureofmedia.task.contactsapi.contacts.entities.Contact;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    @Mapping(target = "companyName", source = "company.name")
    ContactListItemDto entityToListItemDto(Contact entity);

    @Mapping(target = "companyId", source = "company.id")
    ContactDto entityToDto(Contact entity);

    @Mapping(target = "companyName", source = "company.name")
    ContactDetailsDto entityToDetailsDto(Contact entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Contact dtoToEntityForCreate(ContactCreateAndUpdateDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    void updateContact(@MappingTarget Contact entity, ContactCreateAndUpdateDto dto);


}
