package com.kp.order.service.tools;

import com.kp.order.service.dto.OrderDto;
import com.kp.order.service.dto.OrderItemsDto;
import com.kp.order.service.entity.Order;
import com.kp.order.service.entity.OrderItems;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public OrderDto mapToOrderDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    public OrderItems mapToOrderItems(OrderItemsDto orderItemsDto) {
        return modelMapper.map(orderItemsDto, OrderItems.class);
    }

}
