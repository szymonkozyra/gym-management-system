package com.sii.gym.gymmanagementsystem.dto;

import java.math.BigDecimal;

// Represents a monthly revenue for a gym
public record RevenueReportDTO(
        String gymName,
        BigDecimal amount,
        String currency
) {}