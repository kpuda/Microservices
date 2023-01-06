package com.microservices.db.microservice.services;

import com.microservices.db.microservice.models.User;
import com.microservices.db.microservice.models.UserDto;
import com.microservices.db.microservice.responses.WrappedResponseObjectV1;
import com.microservices.db.microservice.repositories.UserRepository;
import com.microservices.db.microservice.responses.ResponseObjectV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public ResponseObjectV1 saveUser(UserDto userDto) {
        log.info("Receiving userDto from rest service");
        User user = Mapper.mapToUser(userDto);
        userRepository.save(user);
        return new ResponseObjectV1(HttpStatus.CREATED.value(), "New user created");
    }

    public WrappedResponseObjectV1 getUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserDto> userDtoList = allUsers.stream().map(Mapper::mapToUserDto).collect(Collectors.toList());
        return new WrappedResponseObjectV1(HttpStatus.OK.value(), "Users fetched successfully", userDtoList);
    }

}
