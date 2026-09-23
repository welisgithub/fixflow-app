package com.fixflow.backend.domain.repository;

import com.fixflow.backend.domain.entity.OrderAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderAttachmentRepository extends JpaRepository<OrderAttachment, Long> {
    List<OrderAttachment> findByServiceOrderId(Long serviceOrderId);
}