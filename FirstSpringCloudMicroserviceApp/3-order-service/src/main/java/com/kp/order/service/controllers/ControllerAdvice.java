package com.kp.order.service.controllers;

import com.kp.order.service.exceptions.NoContentException;
import com.kp.order.service.responses.ResponseObject;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseObject entityExistsException(Exception message) {
        log.info("Throwing entity exists exception");
        return new ResponseObject(HttpStatus.CONFLICT.value(), message.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseObject entityNotFoundException(Exception message) {
        log.info("Throwing entity not foundexception");
        return new ResponseObject(HttpStatus.CONFLICT.value(), message.getMessage());
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<?> noContentException(Exception message) {
        log.info("Throwing NoContentException exception");
        return new ResponseEntity<>(new ResponseObject(HttpStatus.NO_CONTENT.value(), message.getMessage()), HttpStatus.NO_CONTENT);
    }


}
