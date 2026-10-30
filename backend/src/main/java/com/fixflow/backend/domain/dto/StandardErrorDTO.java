package com.fixflow.backend.domain.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record StandardErrorDTO(
        Integer status,
        String message,
        LocalDateTime timestamp,
        Map<String, String> errors
) {
    public static StandardErrorDTO of(Integer status, String message) {
        return new StandardErrorDTO(status, message, LocalDateTime.now(), null);
    }

    public static StandardErrorDTO of(Integer status, String message, Map<String, String> errors) {
        return new StandardErrorDTO(status, message, LocalDateTime.now(), errors);
    }
}