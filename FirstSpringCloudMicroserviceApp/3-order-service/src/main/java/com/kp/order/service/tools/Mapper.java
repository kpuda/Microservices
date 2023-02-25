package com.kp.order.service.tools;

import com.kp.order.service.dto.OrderDto;
import com.kp.order.service.dto.ItemsDto;
import com.kp.order.service.entity.Order;
import com.kp.order.service.entity.Items;
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

    public Items mapToOrderItems(ItemsDto itemsDto) {
        return modelMapper.map(itemsDto, Items.class);
    }

}
