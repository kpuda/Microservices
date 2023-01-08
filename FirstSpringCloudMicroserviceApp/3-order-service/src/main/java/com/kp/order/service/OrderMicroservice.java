package com.kp.order.service;

import com.kp.order.service.entity.Order;
import com.kp.order.service.entity.OrderItems;
import com.kp.order.service.repositories.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@EnableFeignClients
@SpringBootApplication
public class OrderMicroservice {

    public static void main(String[] args) {
        SpringApplication.run(OrderMicroservice.class, args);
    }

    @Bean
    public CommandLineRunner loadData(OrderRepository orderRepository) {
        return args -> {
            Order order = new Order(1l, 1l, UUID.randomUUID().toString(), List.of(new OrderItems(1l, "milk", BigDecimal.valueOf(2.22), 2), new OrderItems(2l, "Eggs", BigDecimal.valueOf(10.23), 3)));
            Order order2 = new Order(2l, 1l, UUID.randomUUID().toString(), List.of(new OrderItems(3l, "coffee", BigDecimal.valueOf(2.22), 2), new OrderItems(4l, "Bacon", BigDecimal.valueOf(10.23), 3)));
            orderRepository.save(order);
            orderRepository.save(order2);
        };
    }
}
