package com.kp.api.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.RequestPath;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;

@Slf4j
@Component
public class MyPreFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("First PreFilter is being executed...");

        RequestPath path = exchange.getRequest().getPath();
        log.info("Request path: " + path);

        HttpHeaders headers = exchange.getRequest().getHeaders();
        Set<String> headerNames = headers.keySet();
        headerNames.forEach(headerName -> {
            String headerValue = headers.getFirst(headerName);
            log.info(headerName + ": " + headerValue);
        });
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        //This filter will be executed first because of order 0
        return 0;
    }
}
