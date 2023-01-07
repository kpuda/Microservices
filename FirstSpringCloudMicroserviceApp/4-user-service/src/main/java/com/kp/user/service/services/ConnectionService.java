package com.kp.user.service.services;

import com.kp.user.service.responses.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ConnectionService {

    private final RestTemplate restTemplate;

}
