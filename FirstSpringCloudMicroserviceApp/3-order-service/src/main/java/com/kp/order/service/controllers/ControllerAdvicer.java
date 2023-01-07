package com.kp.order.service.controllers;

import com.kp.order.service.responses.ResponseObject;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvicer {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseObject entityNotFoundException(Exception message) {
        return new ResponseObject(HttpStatus.NOT_FOUND.value(), message.getMessage());
    }
}
