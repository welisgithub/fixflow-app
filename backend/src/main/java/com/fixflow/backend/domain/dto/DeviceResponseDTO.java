package com.fixflow.backend.domain.dto;

import com.fixflow.backend.domain.entity.Device;

public record DeviceResponseDTO(
        Long id,
        String imei,
        String brand,
        String commercialModel,
        String technicalModel,
        String preExistingDefects,
        Long customerId
) {
    public static DeviceResponseDTO fromEntity(Device device) {
        return new DeviceResponseDTO(
                device.getId(),
                device.getImei(),
                device.getBrand(),
                device.getCommercialModel(),
                device.getTechnicalModel(),
                device.getPreExistingDefects(),
                device.getCustomer().getId()
        );
    }
}