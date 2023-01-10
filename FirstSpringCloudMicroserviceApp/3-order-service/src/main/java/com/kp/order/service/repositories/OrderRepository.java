package com.kp.order.service.repositories;

import com.kp.order.service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<List<Order>> findAllByUserId(Long id);

    Optional<Order> findByIdAndUserId(long orderId, long id);
}
