package com.sii.gym.gymmanagementsystem.exception;

import java.time.LocalDateTime;
import java.util.Map;

// Standardized error response structure
public record ErrorResponse(
        int status,
        String message,
        LocalDateTime timestamp,
        Map<String, String> errors // For validation errors (field, message)
) {
    public ErrorResponse(int status, String message) {
        this(status, message, LocalDateTime.now(), null);
    }

    public ErrorResponse(int status, String message, Map<String, String> errors) {
        this(status, message, LocalDateTime.now(), errors);
    }
}