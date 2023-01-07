package com.kp.user.service.services;

import com.kp.user.service.dto.UserDto;
import com.kp.user.service.entity.User;
import com.kp.user.service.repositories.UserRepository;
import com.kp.user.service.responses.ResponseObject;
import com.kp.user.service.responses.UserResponseObject;
import com.kp.user.service.responses.WrappedResponseObject;
import com.kp.user.service.tools.Mapper;
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
    private final Mapper mapper;

    @Transactional
    public ResponseObject postUser(UserDto userDto) {
        User user = mapper.mapToUser(userDto);
        User save = userRepository.save(user);
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
}
