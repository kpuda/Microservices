package com.kp.order.service.controllers;

import com.kp.order.service.dto.OrderLineItemsDto;
import com.kp.order.service.dto.OrderRequest;
import com.kp.order.service.responses.ResponseObject;
import com.kp.order.service.services.OrderService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<ResponseObject> placeOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(orderRequest);
    }

    @PostConstruct
    void run() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderLineItemsDtoList(List.of(new OrderLineItemsDto(1l, "xd", BigDecimal.ONE, 321)));
        System.out.println(orderRequest.toString());
    }
}
