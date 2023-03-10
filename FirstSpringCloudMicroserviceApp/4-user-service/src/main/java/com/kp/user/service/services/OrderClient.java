package com.kp.user.service.services;

import com.kp.user.service.responses.WrappedResponseObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service", path = "/order")
public interface OrderClient {

    @GetMapping
    public WrappedResponseObject getOrders();

    @GetMapping("/{id}")
    public WrappedResponseObject getOrders(@PathVariable Long id);

    @GetMapping("/{id}/orders/{orderId}")
    public WrappedResponseObject getUserOrder(@PathVariable long id, @PathVariable long orderId);
}
