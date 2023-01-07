package com.kp.order.service.services;

import com.kp.order.service.dto.OrderDto;
import com.kp.order.service.entity.Order;
import com.kp.order.service.repositories.OrderRepository;
import com.kp.order.service.responses.ResponseObject;
import com.kp.order.service.responses.WrappedResponseObject;
import com.kp.order.service.tools.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final Mapper mapper;

    @Transactional
    public ResponseObject postOrder(OrderDto orderDto) {
        Order user = mapper.mapToOrder(orderDto);
        Order save = orderRepository.save(user);
        return new ResponseObject(HttpStatus.CREATED.value(), "Created");
    }

    @Transactional
    public WrappedResponseObject getDepartaments() {
        List<Order> all = orderRepository.findAll();
        return new WrappedResponseObject(HttpStatus.OK.value(), "List of users", all);
    }
}
