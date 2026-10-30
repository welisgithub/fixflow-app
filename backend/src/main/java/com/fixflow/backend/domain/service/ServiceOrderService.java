package com.fixflow.backend.domain.service;

import com.fixflow.backend.domain.dto.ServiceOrderRequestDTO;
import com.fixflow.backend.domain.dto.ServiceOrderResponseDTO;
import com.fixflow.backend.domain.entity.*;
import com.fixflow.backend.domain.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate; // Necessário para pegar o ano atual
import java.time.LocalDateTime;
import java.util.List;

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

        // --- GERAÇÃO DO NÚMERO DE OS INTELIGENTE (Ex: 0101260001) ---
        String companyCode = "01";
        String branchCode = "01";
        String yearCode = String.format("%02d", LocalDate.now().getYear() % 100); // Pega "26" de 2026

        String prefix = companyCode + branchCode + yearCode; // Resultado: "010126"
        String generatedOrderNumber = generateNextOrderNumber(prefix);
        // -------------------------------------------------------------

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

    // --- MÉTODO AUXILIAR CORRIGIDO PARA CALCULAR O SEQUENCIAL ---
    private String generateNextOrderNumber(String prefix) {
        // Busca a Entidade ServiceOrder mais recente que começa com o prefixo
        ServiceOrder lastOrder = serviceOrderRepository.findTopByOrderNumberStartingWithOrderByOrderNumberDesc(prefix);

        int nextSequence = 1;

        if (lastOrder != null && lastOrder.getOrderNumber() != null) {
            // Extrai a string do orderNumber da última OS e calcula o próximo número
            String lastOrderNumber = lastOrder.getOrderNumber();
            String sequenceStr = lastOrderNumber.substring(prefix.length());
            nextSequence = Integer.parseInt(sequenceStr) + 1;
        }

        // Formata para 4 dígitos com zeros à esquerda (ex: 1 vira "0001")
        String sequenceFormatted = String.format("%04d", nextSequence);

        return prefix + sequenceFormatted;
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