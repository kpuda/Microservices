package com.kp.order.service.services;

import com.kp.order.service.dto.InventoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConnectionService {

    private final InventoryClient inventoryClient;

    public CompletableFuture<List<InventoryResponse>> isInStock(List<String> items) {
        log.info("Connecting to the inventory-service");
        return CompletableFuture.supplyAsync(() -> inventoryClient.isInStock(items));
    }
}
