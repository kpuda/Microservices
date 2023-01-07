package com.kp.user.service.controllers;

import com.kp.user.service.responses.ResponseObject;
import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseObject entityExistsException(Exception message) {
        return new ResponseObject(HttpStatus.CONFLICT.value(), message.getMessage());
    }
}
