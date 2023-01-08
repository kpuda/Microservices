package com.kp.order.service.services;

import com.kp.order.service.dto.InventoryResponse;
import com.kp.order.service.dto.OrderLineItemsDto;
import com.kp.order.service.dto.OrderRequest;
import com.kp.order.service.entity.Order;
import com.kp.order.service.entity.OrderLineItems;
import com.kp.order.service.repositories.OrderRepository;
import com.kp.order.service.responses.ResponseObject;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ConnectionService connectionService;


    @Transactional
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public CompletableFuture<ResponseObject> placeOrder(OrderRequest orderRequest) {
        log.info("New request for placing order");
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> rfidList = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getRfidCode)
                .toList();
        log.info("Checking if items are in stock");
        List<InventoryResponse> inStock = connectionService.isInStock(rfidList);

        boolean allProductsInStock = inStock.stream().allMatch(InventoryResponse::isInStock);

        if (allProductsInStock) {
            log.info("Saving new order");
            orderRepository.save(order);
            return CompletableFuture.supplyAsync(() -> new ResponseObject(HttpStatus.CREATED.value(), "Order Placed Successfully"));
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }
    }

    public CompletableFuture<ResponseObject> fallbackMethod(OrderRequest orderRequest, Throwable runtimeException) {
        return CompletableFuture.supplyAsync(() -> new ResponseObject(HttpStatus.SERVICE_UNAVAILABLE.value(), "Oops! Something went wrong, please order after some time!"));
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setRfidCode(orderLineItemsDto.getRfidCode());
        return orderLineItems;
    }
}
