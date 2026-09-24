package com.fixflow.backend.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record CustomerRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "O CPF/CNPJ é obrigatório")
        String cpfCnpj,

        @NotBlank(message = "O telefone é obrigatório")
        String phone,

        String email
) {}