package com.kp.order.service.exceptions;

public class NoContentException extends RuntimeException{
    public NoContentException(String message) {
        super(message);
    }

    public NoContentException(String message, Throwable cause) {
        super(message, cause);
    }
}
