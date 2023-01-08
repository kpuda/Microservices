package com.kp.order.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class OrderMicroservice {

    public static void main(String[] args) {
        SpringApplication.run(OrderMicroservice.class, args);
    }

}
