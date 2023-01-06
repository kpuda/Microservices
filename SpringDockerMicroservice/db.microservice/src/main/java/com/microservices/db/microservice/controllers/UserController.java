package com.microservices.db.microservice.controllers;

import com.microservices.db.microservice.models.UserDto;
import com.microservices.db.microservice.responses.WrappedResponseObjectV1;
import com.microservices.db.microservice.responses.ResponseObjectV1;
import com.microservices.db.microservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/save")
    public ResponseObjectV1 saveUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @GetMapping("/user")
    public WrappedResponseObjectV1 getUsers() {
        return userService.getUsers();
    }
}
