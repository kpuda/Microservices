package com.microservices.rest.microservice.services;

import com.microservices.rest.microservice.models.UserDto;
import com.microservices.rest.microservice.responses.ResponseObjectV1;
import com.microservices.rest.microservice.responses.WrappedResponseObjectV1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;

@Service
public class RestTemplateService {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${db.url}")
    private String url;
    private final String USER_SAVE_ENDPOINT = "user/save";
    private final String USERS_GET_ENDPOINT = "user/get";

    public ResponseObjectV1 postUser(UserDto userDto) throws ConnectException, HttpClientErrorException, HttpServerErrorException {
        return restTemplate.postForObject(url + USER_SAVE_ENDPOINT, userDto, ResponseObjectV1.class);
    }

    public WrappedResponseObjectV1 getUsers() throws HttpClientErrorException, HttpServerErrorException {
        return restTemplate.getForObject(url + USERS_GET_ENDPOINT, WrappedResponseObjectV1.class);
    }
}
