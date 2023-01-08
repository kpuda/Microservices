package com.kp.user.service.controllers;

import com.kp.user.service.dto.UserDto;
import com.kp.user.service.responses.ResponseObject;
import com.kp.user.service.responses.UserResponseObject;
import com.kp.user.service.responses.WrappedResponseObject;
import com.kp.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseObject postUser(@RequestBody UserDto userDto) {
        return userService.postUser(userDto);
    }

    @GetMapping("/{id}")
    public UserResponseObject getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/{id}/{orderId}")
    public CompletableFuture<WrappedResponseObject> getUserOrder(@PathVariable Long id, @PathVariable Long orderId, final HttpServletResponse response) {
        return userService.getUserOrder(id, orderId, response);
    }

}
