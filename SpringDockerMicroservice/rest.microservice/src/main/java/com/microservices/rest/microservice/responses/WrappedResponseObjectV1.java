package com.microservices.rest.microservice.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WrappedResponseObjectV1 {

    private int statusCode;
    private String message;
    private List<?> list;
}
