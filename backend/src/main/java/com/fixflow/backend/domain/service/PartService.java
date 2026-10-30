package com.fixflow.backend.domain.service;

import com.fixflow.backend.domain.dto.PartRequestDTO;
import com.fixflow.backend.domain.dto.PartResponseDTO;
import com.fixflow.backend.domain.entity.Part;
import com.fixflow.backend.domain.repository.PartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartService {

    private final PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Transactional
    public PartResponseDTO create(PartRequestDTO dto) {
        if (dto.sku() != null && !dto.sku().isBlank()) {
            partRepository.findBySku(dto.sku()).ifPresent(p -> {
                throw new IllegalArgumentException("Já existe uma peça cadastrada com o SKU: " + dto.sku());
            });
        }

        Part part = Part.builder()
                .name(dto.name())
                .description(dto.description())
                .sku(dto.sku())
                .costPrice(dto.costPrice())
                .sellingPrice(dto.sellingPrice())
                .stockQuantity(dto.stockQuantity())
                .minStockQuantity(dto.minStockQuantity())
                .build();

        return PartResponseDTO.fromEntity(partRepository.save(part));
    }

    @Transactional(readOnly = true)
    public List<PartResponseDTO> findAll() {
        return partRepository.findAll().stream()
                .map(PartResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public PartResponseDTO findById(Long id) {
        Part part = partRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Peça não encontrada com ID: " + id));
        return PartResponseDTO.fromEntity(part);
    }
}