package com.sii.gym.gymmanagementsystem.dto;

import com.sii.gym.gymmanagementsystem.model.PlanType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;
import java.math.BigDecimal;

@Builder
public record MembershipPlanDTO(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        Long id,

        @NotBlank(message = "Plan name is required")
        @Schema(example = "Premium Yearly")
        String name,

        @NotNull(message = "Plan type is required")
        @Schema(example = "PREMIUM")
        PlanType type,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "The price must be greater than zero")
        @Schema(example = "199.99")
        BigDecimal price,

        @NotBlank(message = "Currency is required")
        @Size(min = 3, max = 3, message = "Currency must be in 3 letter format")
        @Schema(example = "PLN")
        String currency,

        @NotNull(message = "Duration is required")
        @Min(value = 1, message = "Duration must be at least 1 month")
        @Schema(example = "12")
        Integer durationInMonths,

        @NotNull(message = "Max members limit is required")
        @Min(value = 1, message = "Max members must be at least 1")
        @Schema(example = "100")
        Integer maxMembers,

        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        Long gymId // Foreign key
){}
