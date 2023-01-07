package com.kp.cloud.gateway;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseObject {

    private int statusCode;
    private String message;
}
