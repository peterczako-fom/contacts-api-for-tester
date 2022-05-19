package hu.futureofmedia.task.contactsapi.users.mappers;

import hu.futureofmedia.task.contactsapi.users.dtos.UserCreateAndUpdateDto;
import hu.futureofmedia.task.contactsapi.users.dtos.UserDto;
import hu.futureofmedia.task.contactsapi.users.dtos.UserListItemDto;
import hu.futureofmedia.task.contactsapi.users.entities.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserListItemDto entityToListItemDto(AppUser entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    AppUser dtoToEntityForCreate(UserCreateAndUpdateDto dto);

    UserDto entityToDto(AppUser entity);
}
