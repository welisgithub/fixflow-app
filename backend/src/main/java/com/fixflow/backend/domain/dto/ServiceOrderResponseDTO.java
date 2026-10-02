package com.fixflow.backend.domain.dto;

import com.fixflow.backend.domain.entity.OrderStatus;
import com.fixflow.backend.domain.entity.ServiceOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ServiceOrderResponseDTO(
        Long id,
        String orderNumber,
        OrderStatus status,
        BigDecimal totalValue,
        String technicalReport,
        LocalDateTime entryDate,
        LocalDateTime completionDate,
        Long customerId,
        String customerName,
        Long deviceId,
        String deviceModel,
        Long technicianId,
        String technicianName
) {
    public static ServiceOrderResponseDTO fromEntity(ServiceOrder os) {
        return new ServiceOrderResponseDTO(
                os.getId(),
                os.getOrderNumber(),
                os.getStatus(),
                os.getTotalValue(),
                os.getTechnicalReport(),
                os.getEntryDate(),
                os.getCompletionDate(),
                os.getCustomer().getId(),
                os.getCustomer().getName(),
                os.getDevice().getId(),
                os.getDevice().getBrand() + " " + os.getDevice().getCommercialModel(),
                os.getTechnician() != null ? os.getTechnician().getId() : null,
                os.getTechnician() != null ? os.getTechnician().getName() : null
        );
    }
}