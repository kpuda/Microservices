package com.kp.inventory.service.repositories;

import com.kp.inventory.service.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByRfidIn(List<String> rfidCode);
}
