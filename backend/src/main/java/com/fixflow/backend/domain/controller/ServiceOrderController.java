package com.fixflow.backend.domain.controller;

import com.fixflow.backend.domain.dto.ServiceOrderRequestDTO;
import com.fixflow.backend.domain.dto.ServiceOrderResponseDTO;
import com.fixflow.backend.domain.entity.OrderStatus;
import com.fixflow.backend.domain.service.ServiceOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-orders")
public class ServiceOrderController {

    private final ServiceOrderService serviceOrderService;

    public ServiceOrderController(ServiceOrderService serviceOrderService) {
        this.serviceOrderService = serviceOrderService;
    }

    @PostMapping
    public ResponseEntity<ServiceOrderResponseDTO> create(@RequestBody @Valid ServiceOrderRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceOrderService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ServiceOrderResponseDTO>> findAll() {
        return ResponseEntity.ok(serviceOrderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOrderResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceOrderService.findById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ServiceOrderResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status) {
        return ResponseEntity.ok(serviceOrderService.updateStatus(id, status));
    }
}