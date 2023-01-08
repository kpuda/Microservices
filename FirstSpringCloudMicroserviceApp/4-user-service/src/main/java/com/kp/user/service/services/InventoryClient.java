package com.kp.user.service.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @GetMapping("/inventory")
    public List<String> isInStock(@RequestParam List<String> rfidCode);
}
