package com.kp.order.service.services;

import com.kp.order.service.dto.InventoryResponse;
import com.kp.order.service.dto.OrderLineItemsDto;
import com.kp.order.service.entity.OrderLineItems;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConnectionService {

    private final InventoryClient inventoryClient;

    public List<InventoryResponse> isInStock(List<String> items) {
        log.info("Connecting to the inventory-service");
        return inventoryClient.isInStock(items);
    }
}
