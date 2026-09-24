package com.fixflow.backend.domain.dto;

import com.fixflow.backend.domain.entity.Customer;
import java.time.LocalDateTime;

public record CustomerResponseDTO(
        Long id,
        String name,
        String cpfCnpj,
        String phone,
        String email,
        LocalDateTime createdAt
) {
    public static CustomerResponseDTO fromEntity(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getCpfCnpj(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getCreatedAt()
        );
    }
}