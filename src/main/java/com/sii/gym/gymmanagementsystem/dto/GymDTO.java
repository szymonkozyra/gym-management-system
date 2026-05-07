package com.sii.gym.gymmanagementsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record GymDTO (
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        Long id,

        @NotBlank(message = "Gym name is required")
        @Schema(example = "FitLife Center")
        String name,

        @NotBlank(message = "Address is required")
        @Schema(example = "Sympatyczna 16, Lublin")
        String address,

        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^[0-9+\\s-]{9,15}$", message = "Invalid phone number format")
        @Schema(example = "+48 123 456 789")
        String phoneNumber
) {}