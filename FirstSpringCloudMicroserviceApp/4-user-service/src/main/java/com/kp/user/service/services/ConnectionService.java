package com.kp.user.service.services;

import com.kp.user.service.responses.WrappedResponseObject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConnectionService {

    private final OrderClient orderClient;

    @SneakyThrows
    public CompletableFuture<WrappedResponseObject> getOrders(long id) {
        log.info("Sending request to order client to fetch orders list for given user");
        return CompletableFuture.supplyAsync(() -> orderClient.getOrders(id));
    }

    @SneakyThrows
    public CompletableFuture<WrappedResponseObject> getUserOrder(long id, long orderId) {
        log.info("Sending request to order client to fetch given order");
        return CompletableFuture.supplyAsync(() -> orderClient.getUserOrder(id, orderId));
    }
}
