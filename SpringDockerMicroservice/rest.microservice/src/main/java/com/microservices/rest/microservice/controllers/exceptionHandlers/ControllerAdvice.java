package com.microservices.rest.microservice.controllers.exceptionHandlers;


import com.microservices.rest.microservice.responses.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.ConnectException;
import java.time.LocalDate;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(value = ConnectException.class)
    public final ErrorDetails exception(Exception exception, WebRequest request) {
        log.info("Logging exception: {}", exception.getMessage());
        return new ErrorDetails(LocalDate.now(), exception.getMessage(), request.getDescription(false));
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(value = HttpClientErrorException.class)
    public final ErrorDetails httpClientException(Exception exception, WebRequest request) {
        log.info("Logging exception: {}", exception.getMessage());
        return new ErrorDetails(LocalDate.now(), exception.getMessage(), request.getDescription(false));
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(value = HttpServerErrorException.class)
    public final ErrorDetails httpServerErrorException(Exception exception, WebRequest request) {
        log.info("Logging exception: {}", exception.getMessage());
        return new ErrorDetails(LocalDate.now(), exception.getMessage(), request.getDescription(false));
    }
}
