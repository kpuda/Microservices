package com.kp.user.service.controllers;

import com.kp.user.service.dto.UserDto;
import com.kp.user.service.responses.UserResponseObject;
import com.kp.user.service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity registerUser(@RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }

    @GetMapping("/{id}")
    public UserResponseObject getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

}
