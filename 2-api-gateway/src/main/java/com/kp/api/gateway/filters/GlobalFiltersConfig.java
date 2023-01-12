package com.kp.api.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class GlobalFiltersConfig {

    @Bean
    @Order(1)
    public GlobalFilter secondPreFilter() {
        return (exchange, chain) -> {
            log.info("My second global preFilter is executed...");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("My third global postFilter is executed...");
            }));
        };
    }

    @Bean
    @Order(2)
    public GlobalFilter thirdPreFilter() {
        return (exchange, chain) -> {
            log.info("My third global preFilter is executed...");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("My second global postFilter is executed...");
            }));
        };
    }


    @Bean
    @Order(3)
    public GlobalFilter fourthPreFilter() {
        return (exchange, chain) -> {
            log.info("My fourth global preFilter is executed...");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("My first global postFilter is executed...");
            }));
        };
    }
}
