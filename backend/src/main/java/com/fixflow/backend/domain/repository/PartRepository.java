package com.fixflow.backend.domain.repository;

import com.fixflow.backend.domain.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    Optional<Part> findBySku(String sku);
}