package com.fixflow.backend.domain.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ServiceOrderRequestDTO(
        @NotNull(message = "O ID do cliente é obrigatório")
        Long customerId,

        @NotNull(message = "O ID do aparelho é obrigatório")
        Long deviceId,

        Long technicianId,
        String technicalReport,
        BigDecimal totalValue
) {}