package com.kp.user.service.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class WrappedResponseObject extends ResponseObject {

    private List<?> list;

    public WrappedResponseObject(int statusCode, String message, List<?> list) {
        super(statusCode, message);
        this.list = list;
    }
}

