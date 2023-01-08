package com.kp.inventory.service.services;

import com.kp.inventory.service.dto.InventoryResponse;
import com.kp.inventory.service.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> rfidList) {
        log.info("Checking Inventory");
        return inventoryRepository.findByRfidIn(rfidList).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .rfid(inventory.getRfid())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }

}
