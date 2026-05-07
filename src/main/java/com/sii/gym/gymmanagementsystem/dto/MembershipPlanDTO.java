package com.sii.gym.gymmanagementsystem.dto;

import com.sii.gym.gymmanagementsystem.model.PlanType;
import jakarta.validation.constraints.*;
import lombok.Builder;
import java.math.BigDecimal;

@Builder
public record MembershipPlanDTO(
        Long id,

        @NotBlank(message = "Plan name is required")
        String name,

        @NotNull(message = "Plan type is required")
        PlanType type,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "The price must be greater than zero")
        BigDecimal price,

        @NotBlank(message = "Currency is required")
        @Size(min = 3, max = 3, message = "Currency must be in 3 letter format")
        String currency,

        @NotNull(message = "Duration is required")
        @Min(value = 1, message = "Duration must be at least 1 month")
        Integer durationInMonths,

        @NotNull(message = "Max members limit is required")
        @Min(value = 1, message = "Max members must be at least 1")
        Integer maxMembers,

        Long gymId // Foreign key
){}
