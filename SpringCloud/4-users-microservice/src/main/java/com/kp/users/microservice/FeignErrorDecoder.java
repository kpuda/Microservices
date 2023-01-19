package com.kp.users.microservice;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        System.out.println("ErrorDecoder triggered"); // todo testing purposes
        switch (response.status()) {
            case 400:
                //do something
                break;
            case 404:
                //do something
                //for message you can call response.reason() or some custom todo make some custom exceptions later
                if (methodKey.contains("getUserAlbums")) {
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Users albums not found");
                }
                break;
            case 500:
                //do something
                break;
            case 503:
                //do something
                break;
            default:
                return new Exception(response.reason());
        }
        return null;
    }
}
