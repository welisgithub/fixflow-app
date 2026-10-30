package com.fixflow.backend.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record PartRequestDTO(
        @NotBlank(message = "O nome da peça é obrigatório")
        String name,

        String description,
        String sku,
        BigDecimal costPrice,

        @NotNull(message = "O preço de venda é obrigatório")
        @PositiveOrZero(message = "O preço de venda deve ser maior ou igual a zero")
        BigDecimal sellingPrice,

        @NotNull(message = "A quantidade em estoque é obrigatória")
        @PositiveOrZero(message = "A quantidade deve ser maior ou igual a zero")
        Integer stockQuantity,

        Integer minStockQuantity
) {}