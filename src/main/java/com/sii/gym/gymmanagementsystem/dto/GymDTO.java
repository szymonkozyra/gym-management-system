package com.sii.gym.gymmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record GymDTO (
        Long id,

        @NotBlank(message = "Gym name is required")
        String name,

        @NotBlank(message = "Address is required")
        String address,

        @NotBlank(message = "Phone number is required")
        String phoneNumber
) {}