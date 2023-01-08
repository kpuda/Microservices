package com.kp.user.service.services;

import com.kp.user.service.responses.WrappedResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConnectionService {

    private final OrderClient orderClient;

    public WrappedResponseObject getOrders() {
        return orderClient.getOrders();
    }

}
