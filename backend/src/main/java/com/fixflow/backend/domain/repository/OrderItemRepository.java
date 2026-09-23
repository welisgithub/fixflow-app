package com.fixflow.backend.domain.repository;

import com.fixflow.backend.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByServiceOrderId(Long serviceOrderId);
}