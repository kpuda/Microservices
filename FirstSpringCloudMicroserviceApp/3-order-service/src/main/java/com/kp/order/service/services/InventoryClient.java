package com.kp.order.service.services;

import com.kp.order.service.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @GetMapping("/inventory")
    public List<InventoryResponse> isInStock(@RequestParam List<String> rfidList);

}
