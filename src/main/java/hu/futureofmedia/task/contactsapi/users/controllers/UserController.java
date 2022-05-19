package hu.futureofmedia.task.contactsapi.users.controllers;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import hu.futureofmedia.task.contactsapi.users.dtos.UserCreateAndUpdateDto;
import hu.futureofmedia.task.contactsapi.users.dtos.UserDto;
import hu.futureofmedia.task.contactsapi.users.dtos.UserListDto;
import hu.futureofmedia.task.contactsapi.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        path = "/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto createUser(UserCreateAndUpdateDto dto) {
        return userService.createUser(dto);
    }

    @GetMapping
    public UserListDto getAllUser(
            @RequestParam(value = "page", defaultValue = "0") Integer page
    ) {
        return userService.getAllUsers(page);
    }
}
