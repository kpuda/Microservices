package com.kp.cloud.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/userServiceFallback")
    public ResponseObject userServiceFallback() {
        return new ResponseObject(HttpStatus.SERVICE_UNAVAILABLE.value(), "Request for user service takes longer than expected. Please try again later.");
    }


    @GetMapping("/orderServiceFallback")
    public ResponseObject orderServiceFallback() {
        return new ResponseObject(HttpStatus.SERVICE_UNAVAILABLE.value(), "Request for order service takes longer than expected. Please try again later.");
    }
}
