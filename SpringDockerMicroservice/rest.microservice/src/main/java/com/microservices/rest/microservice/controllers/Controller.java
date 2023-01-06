package com.microservices.rest.microservice.controllers;

import com.microservices.rest.microservice.models.UserDto;
import com.microservices.rest.microservice.responses.ResponseObjectV1;
import com.microservices.rest.microservice.responses.ResponseObjectV2;
import com.microservices.rest.microservice.responses.WrappedResponseObjectV1;
import com.microservices.rest.microservice.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.ConnectException;
import java.util.Enumeration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class Controller {

    private final UserService userService;

    /* First way to version the API - with request mapping /v1 /v2 */
    @GetMapping(value = "/v1/response")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseObjectV1 getFirstVerByMapping(HttpServletRequest request, HttpServletResponse response) {
        return userService.firstVer(request, response);
    }

    @GetMapping(value = "/v2/response")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseObjectV2 getSecondVerByMapping(HttpServletRequest request, HttpServletResponse response) {
        return userService.secondVer(request, response);
    }

    /* Second way to version the API - with request params  foe example ?version=1  */
    @GetMapping(value = "/response", params = "version=1")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseObjectV1 getFirstVerRequestParameter(HttpServletRequest request, HttpServletResponse response) {
        return userService.firstVer(request, response);
    }

    @GetMapping(value = "/response", params = "version=2")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseObjectV2 getSecondVerRequestParameter(HttpServletRequest request, HttpServletResponse response) {
        return userService.secondVer(request, response);
    }

    /* Third way to version the API - with header param for example key: X-API-VERSION value: 1 or 2 */
    @GetMapping(value = "/response/header", headers = "X-API-VERSION=1")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseObjectV1 getFirstVerRequestHeader(HttpServletRequest request, HttpServletResponse response) {
        return userService.firstVer(request, response);
    }

    @GetMapping(value = "/response/header", headers = "X-API-VERSION=2")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseObjectV2 getSecondVerRequestHeader(HttpServletRequest request, HttpServletResponse response) {
        Enumeration<String> headers = request.getHeaderNames();
        return userService.secondVer(request, response);
    }

    /* Fourth way to version the API - with header accept */
    @GetMapping(value = "/response/accept", produces = "application/kp.company.app-v1+json")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseObjectV1 getFirstVerRequestAcceptHeader(HttpServletRequest request, HttpServletResponse response) {
        return userService.firstVer(request, response);
    }

    @GetMapping(value = "/response/accept", produces = "application/kp.company.app-v2+json")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseObjectV2 getSecondVerRequestAcceptHeader(HttpServletRequest request, HttpServletResponse response) {
        Enumeration<String> headers = request.getHeaderNames();
        return userService.secondVer(request, response);
    }

    @PostMapping("/user/add")
    public ResponseObjectV1 saveUser(@RequestBody UserDto userDto) throws ConnectException {
        return userService.saveUser(userDto);
    }

    @GetMapping("/user/get")
    public WrappedResponseObjectV1 getUsers() throws ConnectException {
        return userService.getUsers();
    }

}
