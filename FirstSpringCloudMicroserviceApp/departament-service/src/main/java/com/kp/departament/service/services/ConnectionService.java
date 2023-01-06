package com.kp.departament.service.services;

import com.kp.departament.service.responses.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ConnectionService {

    private final RestTemplate restTemplate;

    public void getDepartament(long id) {
        ResponseObject forObject = restTemplate.getForObject("http://DEPARTAMENT-SERVICE/departament", ResponseObject.class);
    }
}
