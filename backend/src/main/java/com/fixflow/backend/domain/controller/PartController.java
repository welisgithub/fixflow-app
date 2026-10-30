package com.fixflow.backend.domain.controller;

import com.fixflow.backend.domain.dto.PartRequestDTO;
import com.fixflow.backend.domain.dto.PartResponseDTO;
import com.fixflow.backend.domain.service.PartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parts")
public class PartController {

    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @PostMapping
    public ResponseEntity<PartResponseDTO> create(@RequestBody @Valid PartRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<PartResponseDTO>> findAll() {
        return ResponseEntity.ok(partService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(partService.findById(id));
    }
}