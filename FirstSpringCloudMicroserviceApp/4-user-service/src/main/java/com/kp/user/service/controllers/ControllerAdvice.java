package com.kp.user.service.controllers;

import com.kp.user.service.responses.ResponseObject;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseObject entityExistsException(Exception message) {
        return new ResponseObject(HttpStatus.CONFLICT, message.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseObject entityNotFoundException(Exception message) {
        return new ResponseObject(HttpStatus.CONFLICT, message.getMessage());
    }


}
