package com.sii.gym.gymmanagementsystem.mapper;

import com.sii.gym.gymmanagementsystem.dto.MembershipPlanDTO;
import com.sii.gym.gymmanagementsystem.model.MembershipPlan;
import org.springframework.stereotype.Component;

@Component
public class MembershipPlanMapper {

    public MembershipPlanDTO toDTO(MembershipPlan plan) {
        return MembershipPlanDTO.builder()
                .id(plan.getId())
                .name(plan.getName())
                .type(plan.getType())
                .price(plan.getPrice())
                .currency(plan.getCurrency())
                .durationInMonths(plan.getDurationInMonths())
                .maxMembers(plan.getMaxMembers())
                .gymId(plan.getGym().getId())
                .build();
    }

    public MembershipPlan toEntity(MembershipPlanDTO dto) {
        return MembershipPlan.builder()
                .name(dto.name())
                .type(dto.type())
                .price(dto.price())
                .currency(dto.currency())
                .durationInMonths(dto.durationInMonths())
                .maxMembers(dto.maxMembers())
                .build();
    }
}