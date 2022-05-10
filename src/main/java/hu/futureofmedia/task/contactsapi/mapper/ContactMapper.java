package hu.futureofmedia.task.contactsapi.mapper;

import hu.futureofmedia.task.contactsapi.dto.ContactCreateAndUpdateDto;
import hu.futureofmedia.task.contactsapi.dto.ContactDto;
import hu.futureofmedia.task.contactsapi.dto.ContactListItemDto;
import hu.futureofmedia.task.contactsapi.entities.Contact;
import hu.futureofmedia.task.contactsapi.service.CompanyService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ContactMapper {

    @Autowired
    public CompanyService companyService;

    @Mapping(target = "company", expression = "java(companyService.findCompanyById(dto.getCompanyId()))")
    public abstract Contact dtoToEntityForCreate(ContactCreateAndUpdateDto dto);

    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "createdDate", source = "createdDate", dateFormat = "YYYY-MM-dd HH:mm:ss Z")
    @Mapping(target = "lastModifiedDate", source = "lastModifiedDate", dateFormat = "YYYY-MM-dd HH:mm:ss Z")
    public abstract ContactDto entityToDto(Contact entity);

    @Mapping(target = "fullName", expression = "java(entity.getFirstName() + \" \" + entity.getLastName())")
    @Mapping(target = "companyName", source = "company.name")
    public abstract ContactListItemDto entityToListItemDto(Contact entity);

    @Mapping(target = "company", expression = "java(companyService.findCompanyById(dto.getCompanyId()))")
    public abstract void updateContact(@MappingTarget Contact entity, ContactCreateAndUpdateDto dto);
}
