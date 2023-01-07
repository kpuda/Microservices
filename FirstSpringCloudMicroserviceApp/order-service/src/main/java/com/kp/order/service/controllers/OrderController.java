package com.kp.order.service.controllers;

import com.kp.order.service.dto.OrderDto;
import com.kp.order.service.responses.ResponseObject;
import com.kp.order.service.responses.WrappedResponseObject;
import com.kp.order.service.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseObject postOrder(@RequestBody OrderDto orderDto) {
        return orderService.postOrder(orderDto);
    }

    @GetMapping("/all")
    public WrappedResponseObject getUsers() {
        return orderService.getDepartaments();
    }
}
