package com.fixflow.backend.domain.repository;

import com.fixflow.backend.domain.entity.OrderStatus;
import com.fixflow.backend.domain.entity.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {
    Optional<ServiceOrder> findByOrderNumber(String orderNumber);
    List<ServiceOrder> findByCustomerId(Long customerId);
    List<ServiceOrder> findByStatus(OrderStatus status);
}