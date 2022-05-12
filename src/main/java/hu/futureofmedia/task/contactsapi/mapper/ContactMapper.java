package hu.futureofmedia.task.contactsapi.mapper;

import hu.futureofmedia.task.contactsapi.dto.ContactCreateAndUpdateDto;
import hu.futureofmedia.task.contactsapi.dto.ContactDetailsDto;
import hu.futureofmedia.task.contactsapi.dto.ContactDto;
import hu.futureofmedia.task.contactsapi.dto.ContactListItemDto;
import hu.futureofmedia.task.contactsapi.entities.Contact;
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
