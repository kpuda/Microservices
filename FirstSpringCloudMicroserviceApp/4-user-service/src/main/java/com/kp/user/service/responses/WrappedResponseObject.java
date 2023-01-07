package com.kp.user.service.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class WrappedResponseObject extends ResponseObject {

    private List<?> list;

    public WrappedResponseObject(int statusCode, String message, List<?> list) {
        super(statusCode, message);
        this.list = list;
    }
}

