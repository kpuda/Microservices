package com.kp.order.service.services;

import com.kp.order.service.dto.OrderModel;
import com.kp.order.service.entity.Order;
import com.kp.order.service.repositories.OrderRepository;
import com.kp.order.service.responses.ResponseObject;
import com.kp.order.service.responses.UserResponseObject;
import com.kp.order.service.responses.WrappedResponseObject;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ConnectionService connectionService;

    @Transactional
    public ResponseEntity<?> postOrder(OrderModel orderModel) {
        UserResponseObject userResponse;
        try {
            userResponse = connectionService.getUser(orderModel.getUserId());
        } catch (HttpStatusCodeException exception) {
            return ResponseEntity.status(exception.getStatusCode()).headers(exception.getResponseHeaders())
                    .body(exception.getResponseBodyAsString());
        }

        return ResponseEntity.ok(new ResponseObject(HttpStatus.CREATED.value(), "Created"));
    }

    @Transactional
    public WrappedResponseObject getDepartaments() {
        List<Order> all = orderRepository.findAll();
        return new WrappedResponseObject(HttpStatus.OK.value(), "List of users", all);
    }
}
