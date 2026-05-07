package com.sii.gym.gymmanagementsystem.dto;

import com.sii.gym.gymmanagementsystem.model.MemberStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import java.time.LocalDate;

@Builder
public record MemberDTO(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        Long id,

        @NotBlank(message = "Full name is required")
        @Schema(example = "Adam Kowalski")
        String fullName,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Schema(example = "adam.kowalski@example.com")
        String email,

        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        LocalDate startDate,
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        MemberStatus status,

        // Additional fields required by the specification
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        String planName,
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        String gymName,

        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        Long membershipPlanId
) {}