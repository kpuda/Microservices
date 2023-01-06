package com.microservices.rest.microservice.services;

import com.microservices.rest.microservice.models.UserDto;
import com.microservices.rest.microservice.responses.ResponseObjectV1;
import com.microservices.rest.microservice.responses.ResponseObjectV2;
import com.microservices.rest.microservice.responses.WrappedResponseObjectV1;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.ConnectException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final RestTemplateService restService;

    public ResponseObjectV1 firstVer(HttpServletRequest request, HttpServletResponse response) {
        log.info("Returning first version of ResponseObject");
        return new ResponseObjectV1(HttpStatus.OK.value(), "Response object v1");
    }

    public ResponseObjectV2 secondVer(HttpServletRequest request, HttpServletResponse response) {
        log.info("Returning second version of ResponseObject");
        return new ResponseObjectV2(HttpStatus.OK.value(), "Response object v2");
    }

    public ResponseObjectV1 saveUser(UserDto userDto) throws ConnectException {
        log.info("Posting user to different service");
        return restService.postUser(userDto);
    }

    public WrappedResponseObjectV1 getUsers() {
        log.info("Getting user list");
        return restService.getUsers();
    }
}
