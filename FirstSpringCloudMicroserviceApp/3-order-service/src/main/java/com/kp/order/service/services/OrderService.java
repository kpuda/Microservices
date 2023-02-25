package com.kp.order.service.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kp.order.service.dto.InventoryResponse;
import com.kp.order.service.dto.OrderRequest;
import com.kp.order.service.entity.Order;
import com.kp.order.service.entity.Items;
import com.kp.order.service.repositories.OrderRepository;
import com.kp.order.service.responses.ResponseObject;
import com.kp.order.service.responses.UserResponseObject;
import com.kp.order.service.responses.WrappedResponseObject;
import com.kp.order.service.tools.Mapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final Mapper mapper;
    private final OrderRepository orderRepository;
    private final ConnectionService connectionService;
    private final Gson gson;


    @SneakyThrows
    @Transactional
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public ResponseEntity<ResponseObject> placeOrder(OrderRequest orderRequest, HttpServletResponse response, HttpServletRequest request) {
        log.info(String.format("New request for placing order for user with id %s", orderRequest.getUserId()));
        UserResponseObject userResponseObject = (UserResponseObject) connectionService.isUserAvailable(orderRequest.getUserId()).get();
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .userId(userResponseObject.getUserDto().getId())
                .orderItemsList(orderRequest.getOrderList().stream().map(mapper::mapToOrderItems).toList())
                .build();
        List<String> rfidList = order.getOrderItemsList().stream()
                .map(Items::getRfidCode)
                .toList();
        log.info("Checking if items are in stock");

        List<InventoryResponse> inStock = connectionService.isInStock(rfidList).get();
        if (!inStock.isEmpty() && inStock.stream().allMatch(InventoryResponse::isInStock)) {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        } else {
            log.info("Saving new order");
            orderRepository.save(order);
            return new ResponseEntity<>(new ResponseObject(HttpStatus.CREATED, "Order Placed Successfully"), HttpStatus.OK);
        }
    }

    @CircuitBreaker(name = "inventory", fallbackMethod = "userOrdersFallbackMethod")
    public WrappedResponseObject getUserOrders(Long id) {
        log.info("Fetching order list for user");
        List<Order> orderList = orderRepository.findAllByUserId(id).orElseThrow(() -> new EntityNotFoundException("no orders for this user"));
        return new WrappedResponseObject(HttpStatus.OK, "Fetching orders list", orderList.stream().map(mapper::mapToOrderDto).toList());
    }

//    @CircuitBreaker(name = "inventory", fallbackMethod = "userOrderFallbackMethod")
    public WrappedResponseObject getUserOrder(long id, long orderId) {
        log.info("Fetching given order for user");
        Order order = orderRepository.findByIdAndUserId(orderId, id).orElseThrow(()-> new EntityNotFoundException("No order with given id for this user"));
        return new WrappedResponseObject(HttpStatus.OK, "Order fetched.", List.of(mapper.mapToOrderDto(order)));
    }

    /* FALLBACK METHODS */
    public ResponseEntity<ResponseObject> fallbackMethod(OrderRequest orderRequest, HttpServletResponse response, HttpServletRequest request, Throwable runtimeException) {
        log.info("Fallback method");
        ResponseObject responseObject = mapExceptionToObject(runtimeException);
        return new ResponseEntity<>(responseObject, responseObject.getStatusCode());
    }

    public WrappedResponseObject userOrdersFallbackMethod(Long id, Throwable runtimeException) {
        return new WrappedResponseObject(HttpStatus.NO_CONTENT, "There was problem while fetching user orders. Problem details: "+runtimeException.getMessage(), null);
    }

    public WrappedResponseObject userOrderFallbackMethod(Long id, long orderId, Throwable runtimeException) {
        return new WrappedResponseObject(HttpStatus.NO_CONTENT, "There was problem while fetching given order", null);
    }

    private ResponseObject mapExceptionToObject(Throwable runtimeException) {
        String exceptionMessage = runtimeException.getCause().getMessage();
        log.info(String.format("Message from exception: %s", exceptionMessage));
        if (exceptionMessage.contains("[503")) {
            return new ResponseObject(HttpStatus.SERVICE_UNAVAILABLE, "Service unavailable");
        } else {
            exceptionMessage = exceptionMessage.substring(exceptionMessage.indexOf("[{"));
            exceptionMessage = exceptionMessage.replaceAll("[\\[\\]]", "");
            return gson.fromJson(exceptionMessage, ResponseObject.class);
        }
    }
}
