package com.kp.user.service.services;

import com.kp.user.service.ServerConst;
import com.kp.user.service.dto.UserDto;
import com.kp.user.service.entity.User;
import com.kp.user.service.repositories.UserRepository;
import com.kp.user.service.responses.ResponseObject;
import com.kp.user.service.responses.UserResponseObject;
import com.kp.user.service.responses.WrappedResponseObject;
import com.kp.user.service.tools.Mapper;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ConnectionService connectionService;
    private final Mapper mapper;

    @Transactional
    public ResponseObject postUser(UserDto userDto) {
        Optional<User> byFirstNameAndLastName = userRepository.findByFirstNameAndLastName(userDto.getFirstName(), userDto.getLastName());
        if (byFirstNameAndLastName.isPresent()) {
            throw new EntityExistsException(ServerConst.USER_EXISTS_ALREADY.toString());
        }
        userRepository.save(mapper.mapToUser(userDto));
        return new ResponseObject(HttpStatus.CREATED.value(), "Created");
    }

    @Transactional
    public WrappedResponseObject getUsers() {
        List<User> all = userRepository.findAll();
        return new WrappedResponseObject(HttpStatus.OK.value(), "List of users", all);
    }

    public UserResponseObject getUser(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        UserDto userDto = mapper.maptoUserDto(byId.get());
        return new UserResponseObject(HttpStatus.OK.value(), "User found", userDto);
    }

    @PostConstruct
    void addUser() {
        UserDto userDto = new UserDto("James", "Blake");
        userRepository.save(mapper.mapToUser(userDto));
    }
}
