package com.kp.order.service.services;

import com.kp.order.service.responses.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ConnectionService {

    private final RestTemplate restTemplate;

    public void getDepartament(long id) {
        ResponseObject forObject = restTemplate.getForObject("http://USER-SERVICE/user", ResponseObject.class);
    }
}
