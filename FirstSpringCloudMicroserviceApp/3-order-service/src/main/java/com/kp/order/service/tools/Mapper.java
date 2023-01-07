package com.kp.order.service.tools;

import com.kp.order.service.dto.OrderDto;
import com.kp.order.service.entity.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final ModelMapper mapper;

    public Order mapToOrder(OrderDto orderDto) {
        return mapper.map(orderDto, Order.class);
    }
}
