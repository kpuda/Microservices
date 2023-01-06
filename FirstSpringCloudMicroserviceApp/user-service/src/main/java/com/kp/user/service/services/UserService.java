package com.kp.user.service.services;

import com.kp.user.service.dto.UserDto;
import com.kp.user.service.entity.User;
import com.kp.user.service.repositories.UserRepository;
import com.kp.user.service.responses.ResponseObject;
import com.kp.user.service.responses.WrappedResponseObject;
import com.kp.user.service.tools.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
