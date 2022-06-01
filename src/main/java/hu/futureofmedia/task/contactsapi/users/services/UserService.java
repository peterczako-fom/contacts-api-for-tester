package hu.futureofmedia.task.contactsapi.users.services;

import hu.futureofmedia.task.contactsapi.users.dtos.UserCreateAndUpdateDto;
import hu.futureofmedia.task.contactsapi.users.dtos.UserDto;
import hu.futureofmedia.task.contactsapi.users.dtos.UserListDto;

public interface UserService {

    UserListDto getAllUsers(Integer page);

    UserDto createUser(UserCreateAndUpdateDto user);

    UserDto getUserByEmailOrThrow(String username);

}
