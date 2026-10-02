package com.fixflow.backend.domain.controller;

import com.fixflow.backend.domain.dto.DeviceRequestDTO;
import com.fixflow.backend.domain.dto.DeviceResponseDTO;
import com.fixflow.backend.domain.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public ResponseEntity<DeviceResponseDTO> create(@RequestBody @Valid DeviceRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(deviceService.create(dto));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<DeviceResponseDTO>> findByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(deviceService.findByCustomerId(customerId));
    }
}