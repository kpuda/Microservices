package com.kp.user.service.services;

import com.kp.user.service.responses.WrappedResponseObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.concurrent.CompletableFuture;

@FeignClient(name = "order-service")
public interface OrderClient {

    @GetMapping("/order")
    public WrappedResponseObject getOrders();

    @GetMapping("/order/{id}")
    public CompletableFuture<WrappedResponseObject> getOrders(@PathVariable Long id);

    @GetMapping("/order/{id}/{orderId}")
    public CompletableFuture<WrappedResponseObject> getUserOrder(@PathVariable long id,@PathVariable long orderId);
}
