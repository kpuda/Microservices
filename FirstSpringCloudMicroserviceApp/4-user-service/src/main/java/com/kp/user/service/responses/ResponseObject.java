package com.kp.user.service.responses;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObject {

    protected int statusCode;
    protected String message;

}
