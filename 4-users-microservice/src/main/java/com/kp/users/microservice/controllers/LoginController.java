package com.kp.users.microservice.controllers;

import com.kp.users.microservice.model.AuthenticationResponse;
import com.kp.users.microservice.model.LoginModel;
import com.kp.users.microservice.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class LoginController {
    private final AuthService authenticationService;

    @PostMapping("/login")
    public AuthenticationResponse authenticateUser(@RequestBody LoginModel request, HttpServletResponse response) {
        return authenticationService.authenticate(request, response);
    }
}
