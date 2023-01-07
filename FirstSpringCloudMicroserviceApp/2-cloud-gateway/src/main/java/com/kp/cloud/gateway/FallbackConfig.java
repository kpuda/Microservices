package com.kp.cloud.gateway;

import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;


//@Configuration !!TODO!! This is the way of reactive responses. This works aswell if you uncomment config and bean annotations
public class FallbackConfig {

//    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions
                .route(RequestPredicates.GET("/fallback/userServiceFallback"),
                        this::handleGetFallback)
                .andRoute(RequestPredicates.POST("/book-fallback"),
                        this::handlePostFallback);
    }

    public Mono<ServerResponse> handleGetFallback(ServerRequest request) {
        return ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body(Mono.just(new ResponseObject(HttpStatus.SERVICE_UNAVAILABLE.value(), "Request takes longer than expected. Please try again later.")), ResponseObject.class);
    }

    public Mono<ServerResponse> handlePostFallback(ServerRequest request) {
        return ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
}
