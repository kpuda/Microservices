package com.kp.user.service.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WrappedResponseObject extends ResponseObject {

    private List<?> list;

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

