package hu.futureofmedia.task.contactsapi.users.services;

import hu.futureofmedia.task.contactsapi.exception.IdNotFoundException;
import hu.futureofmedia.task.contactsapi.users.dtos.UserCreateAndUpdateDto;
import hu.futureofmedia.task.contactsapi.users.dtos.UserDto;
import hu.futureofmedia.task.contactsapi.users.dtos.UserListDto;
import hu.futureofmedia.task.contactsapi.users.entities.AppUser;
import hu.futureofmedia.task.contactsapi.users.mappers.UserMapper;
import hu.futureofmedia.task.contactsapi.users.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserServiceInner {

    private final AppUserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder encoder;

    @Override
    public UserListDto getAllUsers(Integer page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("email", "id").ascending());
        return new UserListDto(
                userRepository.findAll(pageable).map(userMapper::entityToListItemDto)
        );
    }

    @Override
    public UserDto createUser(UserCreateAndUpdateDto dto) {
        AppUser toSave = userMapper.dtoToEntityForCreate(dto);
        toSave.setPassword(encoder.encode(dto.getPassword()));

        AppUser saved = userRepository.save(toSave);
        return userMapper.entityToDto(saved);
    }

    @Override
    public UserDto getUserByEmailOrThrow(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::entityToDto)
                .orElseThrow(() -> new IdNotFoundException(null, AppUser.class));
    }

    @Override
    public AppUser findUserByEmailOrThrow(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IdNotFoundException(null, AppUser.class));
    }

}