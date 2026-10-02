package com.fixflow.backend.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DeviceRequestDTO(
        String imei,

        @NotBlank(message = "A marca é obrigatória")
        String brand,

        @NotBlank(message = "O modelo comercial é obrigatório")
        String commercialModel,

        String technicalModel,
        String preExistingDefects,

        @NotNull(message = "O ID do cliente é obrigatório")
        Long customerId
) {}