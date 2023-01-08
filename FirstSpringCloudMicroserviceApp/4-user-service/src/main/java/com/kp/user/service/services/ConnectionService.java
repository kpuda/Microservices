package com.kp.user.service.services;

import com.kp.user.service.responses.WrappedResponseObject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ConnectionService {

    private final OrderClient orderClient;

    @SneakyThrows
    public CompletableFuture<WrappedResponseObject> getOrders() {
        return CompletableFuture.supplyAsync(orderClient::getOrders);
    }

}
