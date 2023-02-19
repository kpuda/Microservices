package com.kp.order.service.services;

import com.kp.order.service.dto.InventoryResponse;
import com.kp.order.service.responses.UserResponseObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "user-service", path = "/users")
public interface UserClient {

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id);

}
