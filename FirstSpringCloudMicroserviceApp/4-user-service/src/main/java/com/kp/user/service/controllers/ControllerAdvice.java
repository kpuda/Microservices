package com.kp.user.service.controllers;

import com.kp.user.service.responses.ResponseObject;
import com.kp.user.service.responses.WrappedResponseObject;
import feign.FeignException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public WrappedResponseObject entityNotFoundException(Exception message) {
        log.info("Throwing entity not foundexception");
        return new WrappedResponseObject(HttpStatus.CONFLICT.value(), message.getMessage(), null);
    }


}
