package com.kp.order.service.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class WrappedResponseObject extends ResponseObject {

    private List<?> list;

    public WrappedResponseObject(int statusCode, String message) {
        super(statusCode, message);
    }

    public WrappedResponseObject(int statusCode, String message, List<?> list) {
        super(statusCode, message);
        this.list = list;
    }

    @Override
    public String toString() {
        return "WrappedResponseObject{" +
                "statusCode: " + statusCode +
                ", message:' " + message + '\'' +
                ", list: " + list +
                '}';
    }
}

