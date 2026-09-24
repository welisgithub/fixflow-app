package com.fixflow.backend.domain.service;

import com.fixflow.backend.domain.dto.CustomerRequestDTO;
import com.fixflow.backend.domain.dto.CustomerResponseDTO;
import com.fixflow.backend.domain.entity.Customer;
import com.fixflow.backend.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public CustomerResponseDTO create(CustomerRequestDTO dto) {
        customerRepository.findByCpfCnpj(dto.cpfCnpj())
                .ifPresent(c -> {
                    throw new IllegalArgumentException("Já existe um cliente cadastrado com este CPF/CNPJ.");
                });

        Customer customer = Customer.builder()
                .name(dto.name())
                .cpfCnpj(dto.cpfCnpj())
                .phone(dto.phone())
                .email(dto.email())
                .build();

        Customer savedCustomer = customerRepository.save(customer);
        return CustomerResponseDTO.fromEntity(savedCustomer);
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> findAll() {
        return customerRepository.findAll().stream()
                .map(CustomerResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public CustomerResponseDTO findById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));
        return CustomerResponseDTO.fromEntity(customer);
    }
}