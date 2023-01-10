package com.kp.user.service.services;

import com.kp.user.service.responses.WrappedResponseObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConnectionService {

    private final OrderClient orderClient;

    public ResponseEntity<WrappedResponseObject> getOrders(long id) {
        log.info("Sending request to order client to fetch orders list for given user");
        CompletableFuture<ResponseEntity<WrappedResponseObject>> futureResponse = CompletableFuture.supplyAsync(() -> orderClient.getOrders(id));
        ResponseEntity<WrappedResponseObject> response = null;
        try {
            response = futureResponse.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            log.error("ExecutionException thrown while requesting for Orders list: " + e.getMessage());
        }
        return response;
    }

    public ResponseEntity<WrappedResponseObject> getUserOrder(long id, long orderId) {
        log.info("Sending request to order client to fetch given order");
        CompletableFuture<ResponseEntity<WrappedResponseObject>> responseEntityCompletableFuture = CompletableFuture.supplyAsync(() -> orderClient.getUserOrder(id, orderId));
        ResponseEntity<WrappedResponseObject> wrappedResponseObjectResponseEntity = null;
        try {
            wrappedResponseObjectResponseEntity = responseEntityCompletableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            log.error("ExecutionException thrown while requesting for Order: " + e.getMessage());
        }
        return wrappedResponseObjectResponseEntity;
    }
}
