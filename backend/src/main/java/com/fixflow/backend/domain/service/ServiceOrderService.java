package com.fixflow.backend.domain.service;

import com.fixflow.backend.domain.dto.ServiceOrderRequestDTO;
import com.fixflow.backend.domain.dto.ServiceOrderResponseDTO;
import com.fixflow.backend.domain.entity.*;
import com.fixflow.backend.domain.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import java.util.List;
import java.util.UUID;

@Service
public class ServiceOrderService {

    private final ServiceOrderRepository serviceOrderRepository;
    private final CustomerRepository customerRepository;
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;

    public ServiceOrderService(
            ServiceOrderRepository serviceOrderRepository,
            CustomerRepository customerRepository,
            DeviceRepository deviceRepository,
            UserRepository userRepository) {
        this.serviceOrderRepository = serviceOrderRepository;
        this.customerRepository = customerRepository;
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ServiceOrderResponseDTO create(ServiceOrderRequestDTO dto) {
        Customer customer = customerRepository.findById(dto.customerId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + dto.customerId()));

        Device device = deviceRepository.findById(dto.deviceId())
                .orElseThrow(() -> new IllegalArgumentException("Aparelho não encontrado com ID: " + dto.deviceId()));

        User technician = null;
        if (dto.technicianId() != null) {
            technician = userRepository.findById(dto.technicianId())
                    .orElseThrow(() -> new IllegalArgumentException("Técnico não encontrado com ID: " + dto.technicianId()));
        }

        // Gera um número único de OS, ex: OS-A1B2C3D4
        String generatedOrderNumber = "OS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        ServiceOrder order = ServiceOrder.builder()
                .orderNumber(generatedOrderNumber)
                .customer(customer)
                .device(device)
                .technician(technician)
                .technicalReport(dto.technicalReport())
                .totalValue(dto.totalValue())
                .status(OrderStatus.OPEN)
                .build();

        ServiceOrder savedOrder = serviceOrderRepository.save(order);
        return ServiceOrderResponseDTO.fromEntity(savedOrder);
    }

    @Transactional(readOnly = true)
    public List<ServiceOrderResponseDTO> findAll() {
        return serviceOrderRepository.findAll().stream()
                .map(ServiceOrderResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ServiceOrderResponseDTO findById(Long id) {
        ServiceOrder order = serviceOrderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ordem de Serviço não encontrada com ID: " + id));
        return ServiceOrderResponseDTO.fromEntity(order);
    }

    @Transactional
    public ServiceOrderResponseDTO updateStatus(Long id, OrderStatus newStatus) {
        ServiceOrder order = serviceOrderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ordem de Serviço não encontrada com ID: " + id));

        order.setStatus(newStatus);
        if (newStatus == OrderStatus.DELIVERED || newStatus == OrderStatus.COMPLETED) {
            order.setCompletionDate(LocalDateTime.now());
        }

        return ServiceOrderResponseDTO.fromEntity(serviceOrderRepository.save(order));
    }
}