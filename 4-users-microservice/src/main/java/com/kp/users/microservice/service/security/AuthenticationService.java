package com.kp.users.microservice.service.security;

import com.kp.users.microservice.model.AuthenticationResponse;
import com.kp.users.microservice.model.LoginModel;
import com.kp.users.microservice.service.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthenticationResponse authenticate(LoginModel request, HttpServletResponse response) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())); //TODO custom auth manager?
        SecurityContextHolder.getContext().setAuthentication(authenticate);
       return jwtUtils.setHttpHeaders(authenticate, response);
    }
}
