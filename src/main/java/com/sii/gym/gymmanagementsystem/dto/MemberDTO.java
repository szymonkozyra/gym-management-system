package com.sii.gym.gymmanagementsystem.dto;

import com.sii.gym.gymmanagementsystem.model.MemberStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import java.time.LocalDate;

@Builder
public record MemberDTO(
        Long id,

        @NotBlank(message = "Full name is required")
        String fullName,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        LocalDate startDate,
        MemberStatus status,

        // Additional fields required by the specification
        String planName,
        String gymName,

        Long membershipPlanId
) {}