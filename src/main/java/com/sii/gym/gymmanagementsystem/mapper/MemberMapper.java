package com.sii.gym.gymmanagementsystem.mapper;

import com.sii.gym.gymmanagementsystem.dto.MemberDTO;
import com.sii.gym.gymmanagementsystem.model.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberDTO toDTO(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .fullName(member.getFullName())
                .email(member.getEmail())
                .startDate(member.getStartDate())
                .status(member.getStatus())
                .planName(member.getMembershipPlan().getName())
                .gymName(member.getMembershipPlan().getGym().getName())
                .membershipPlanId(member.getMembershipPlan().getId())
                .build();
    }

    public Member toEntity(MemberDTO dto) {
        return Member.builder()
                .fullName(dto.fullName())
                .email(dto.email())
                // Status and StartDate will be set in the service during registration (Requirement nr 6.2)
                .build();
    }
}