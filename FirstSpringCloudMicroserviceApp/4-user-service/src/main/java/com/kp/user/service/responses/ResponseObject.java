package com.kp.user.service.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject {

    protected int statusCode;
    protected String message;

    @Override
    public String toString() {
        return "ResponseObject{" +
                "statusCode: " + statusCode +
                ", message: '" + message + '\'' +
                '}';
    }
}
