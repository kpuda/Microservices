package com.kp.order.service.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class WrappedResponseObject extends ResponseObject {

    private List<?> list;

    public WrappedResponseObject(HttpStatus statusCode, String message, List<?> list) {
        super(statusCode, message);
        this.list = list;
    }
}

