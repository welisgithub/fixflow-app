package com.fixflow.backend.domain.dto;

import com.fixflow.backend.domain.entity.Part;
import java.math.BigDecimal;

public record PartResponseDTO(
        Long id,
        String name,
        String description,
        String sku,
        BigDecimal costPrice,
        BigDecimal sellingPrice,
        Integer stockQuantity,
        Integer minStockQuantity
) {
    public static PartResponseDTO fromEntity(Part part) {
        return new PartResponseDTO(
                part.getId(),
                part.getName(),
                part.getDescription(),
                part.getSku(),
                part.getCostPrice(),
                part.getSellingPrice(),
                part.getStockQuantity(),
                part.getMinStockQuantity()
        );
    }
}