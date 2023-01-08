package com.kp.order.service.repositories;

import com.kp.order.service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long id);

    Order findByIdAndUserId(long orderId, long id);
}
