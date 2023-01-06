package com.kp.user.service.controllers;

import com.kp.user.service.dto.UserDto;
import com.kp.user.service.responses.ResponseObject;
import com.kp.user.service.responses.WrappedResponseObject;
import com.kp.user.service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseObject postUser(@RequestBody UserDto userDto){
        return userService.postUser(userDto);
    }

    @GetMapping("/all")
    public WrappedResponseObject getUsers(){
        return userService.getUsers();
    }
}
