package com.kp.order.service.controllers;

import com.kp.order.service.dto.OrderModel;
import com.kp.order.service.responses.WrappedResponseObject;
import com.kp.order.service.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> postOrder(@RequestBody OrderModel orderModel) {
        return orderService.postOrder(orderModel);
    }

    @GetMapping
    public WrappedResponseObject getUsers() {
        return orderService.getDepartaments();
    }
}
