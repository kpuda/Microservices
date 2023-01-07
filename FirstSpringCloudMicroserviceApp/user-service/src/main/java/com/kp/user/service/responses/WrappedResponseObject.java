package com.kp.user.service.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class WrappedResponseObject extends ResponseObject {

    private List<?> list;

    public WrappedResponseObject(int statusCode, String message, List<?> list) {
        super(statusCode, message);
        this.list = list;
    }
}
