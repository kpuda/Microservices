package com.kp.users.microservice.controllers;

import com.kp.users.microservice.dto.UserDto;
import com.kp.users.microservice.model.CreateUserRequestModel;
import com.kp.users.microservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/")
public class UserController {

    private final Environment env;
    private final UserService userService;
    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/status/check")
    public String status() {
        return "working on port: " + env.getProperty("local.server.port")+", with token secret: "+env.getProperty("jwt.secret");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@Valid @RequestBody CreateUserRequestModel model) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(model, UserDto.class);
        return userService.createUser(userDto);
    }
}
