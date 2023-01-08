package com.kp.user.service.services;

import com.kp.user.service.responses.WrappedResponseObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "order-service")
public interface OrderClient {

    @GetMapping("/order")
    public WrappedResponseObject getOrders();
}
