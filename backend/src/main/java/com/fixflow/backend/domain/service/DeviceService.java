package com.fixflow.backend.domain.service;

import com.fixflow.backend.domain.dto.DeviceRequestDTO;
import com.fixflow.backend.domain.dto.DeviceResponseDTO;
import com.fixflow.backend.domain.entity.Customer;
import com.fixflow.backend.domain.entity.Device;
import com.fixflow.backend.domain.repository.CustomerRepository;
import com.fixflow.backend.domain.repository.DeviceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final CustomerRepository customerRepository;

    public DeviceService(DeviceRepository deviceRepository, CustomerRepository customerRepository) {
        this.deviceRepository = deviceRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public DeviceResponseDTO create(DeviceRequestDTO dto) {
        Customer customer = customerRepository.findById(dto.customerId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + dto.customerId()));

        Device device = Device.builder()
                .imei(dto.imei())
                .brand(dto.brand())
                .commercialModel(dto.commercialModel())
                .technicalModel(dto.technicalModel())
                .preExistingDefects(dto.preExistingDefects())
                .customer(customer)
                .build();

        Device savedDevice = deviceRepository.save(device);
        return DeviceResponseDTO.fromEntity(savedDevice);
    }

    @Transactional(readOnly = true)
    public List<DeviceResponseDTO> findByCustomerId(Long customerId) {
        return deviceRepository.findByCustomerId(customerId).stream()
                .map(DeviceResponseDTO::fromEntity)
                .toList();
    }
}