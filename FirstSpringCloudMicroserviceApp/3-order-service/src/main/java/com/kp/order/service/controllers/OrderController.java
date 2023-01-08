package com.kp.order.service.controllers;

import com.kp.order.service.dto.OrderRequest;
import com.kp.order.service.responses.ResponseObject;
import com.kp.order.service.responses.WrappedResponseObject;
import com.kp.order.service.services.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseObject> placeOrder(@RequestBody OrderRequest orderRequest, HttpServletResponse response, HttpServletRequest request) {
        return orderService.placeOrder(orderRequest, response, request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WrappedResponseObject getOrders(@PathVariable Long id) {
        return orderService.getUserOrders(id);
    }

    @GetMapping("/{id}/{orderId}")
    public WrappedResponseObject getUserOrder(@PathVariable long id, @PathVariable long orderId) {
        return orderService.getUserOrder(id, orderId);
    }
}
