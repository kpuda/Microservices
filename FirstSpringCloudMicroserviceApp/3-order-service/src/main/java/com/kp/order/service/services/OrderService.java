package com.kp.order.service.services;

import com.kp.order.service.dto.InventoryResponse;
import com.kp.order.service.dto.OrderRequest;
import com.kp.order.service.entity.Order;
import com.kp.order.service.entity.OrderItems;
import com.kp.order.service.exceptions.NoContentException;
import com.kp.order.service.repositories.OrderRepository;
import com.kp.order.service.responses.ResponseObject;
import com.kp.order.service.responses.WrappedResponseObject;
import com.kp.order.service.tools.Mapper;
import com.kp.order.service.tools.ServerConst;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

    @SneakyThrows
    @Transactional
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public ResponseEntity<ResponseObject> placeOrder(OrderRequest orderRequest, HttpServletResponse response, HttpServletRequest request) {
        log.info("New request for placing order");
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setUserId(orderRequest.getUserId());

        order.setOrderItemsList(orderRequest.getOrderList()
                .stream()
                .map(mapper::mapToOrderItems)
                .toList());

        List<String> rfidList = order.getOrderItemsList().stream()
                .map(OrderItems::getRfidCode)
                .toList();
        log.info("Checking if items are in stock");
        List<InventoryResponse> inStock = connectionService.isInStock(rfidList).get();
        boolean allProductsInStock = false;
        if (!inStock.isEmpty()) {
            allProductsInStock = inStock.stream().allMatch(InventoryResponse::isInStock);
        }
        if (allProductsInStock) {
            log.info("Saving new order");
            orderRepository.save(order);
            return new ResponseEntity<>(new ResponseObject(HttpStatus.CREATED.value(), "Order Placed Successfully"), HttpStatus.CREATED);
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }
    }

    public ResponseEntity<WrappedResponseObject> getUserOrders(Long id) {
        log.info("Fetching order list for user");
        List<Order> orderList = orderRepository.findAllByUserId(id).orElseThrow(() -> new NoContentException(ServerConst.LIST_IS_EMPTY.getMessage()));
        if (orderList.isEmpty()) {
            throw new NoContentException(ServerConst.LIST_IS_EMPTY.getMessage());
        }
        return new ResponseEntity<>(new WrappedResponseObject(HttpStatus.OK.value(), ServerConst.ORDER_LIST_FOUND.getMessage(), orderList.stream().map(mapper::mapToOrderDto).toList()), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<WrappedResponseObject> getUserOrder(long id, long orderId) {
        log.info("Fetching given order for user");
        Order order = orderRepository.findByIdAndUserId(orderId, id).orElseThrow(() -> new NoContentException(ServerConst.ORDER_NOT_FOUND.getMessage()));
        return new ResponseEntity<>(new WrappedResponseObject(HttpStatus.OK.value(), "Order fetched.", List.of(mapper.mapToOrderDto(order))), HttpStatus.ALREADY_REPORTED);
    }


    /* FALLBACK METHODS */
    public ResponseEntity<ResponseObject> fallbackMethod(OrderRequest orderRequest, HttpServletResponse response, HttpServletRequest request, Throwable runtimeException) {
        log.info("Fallback method");
        ResponseObject responseObject = new ResponseObject(HttpStatus.SERVICE_UNAVAILABLE.value(), "Inventory service is unavailable at this moment, please order after some time!");
        if (runtimeException.getMessage().equalsIgnoreCase("Product is not in stock, please try again later")) {
            responseObject.setMessage("Cannot fulfill order because of not enough products");
            return new ResponseEntity<>(responseObject, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(responseObject, HttpStatus.SERVICE_UNAVAILABLE);
    }

}
