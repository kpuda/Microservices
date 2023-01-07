package com.kp.order.service.services;

import com.kp.order.service.responses.UserResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ConnectionService {

    private final RestTemplate restTemplate;

    public UserResponseObject getUser(long id) throws HttpStatusCodeException {
        return restTemplate.getForObject("http://USER-SERVICE/users/{id}", UserResponseObject.class, id);
    }
}
