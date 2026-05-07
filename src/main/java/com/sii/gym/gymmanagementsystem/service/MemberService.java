package com.sii.gym.gymmanagementsystem.service;

import com.sii.gym.gymmanagementsystem.dto.MemberDTO;
import com.sii.gym.gymmanagementsystem.exception.BusinessLogicException;
import com.sii.gym.gymmanagementsystem.exception.ResourceNotFoundException;
import com.sii.gym.gymmanagementsystem.mapper.MemberMapper;
import com.sii.gym.gymmanagementsystem.model.Member;
import com.sii.gym.gymmanagementsystem.model.MemberStatus;
import com.sii.gym.gymmanagementsystem.model.MembershipPlan;
import com.sii.gym.gymmanagementsystem.repository.MemberRepository;
import com.sii.gym.gymmanagementsystem.repository.MembershipPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MembershipPlanRepository planRepository;
    private final MemberMapper memberMapper;

    @Transactional
    public MemberDTO registerMember(Long planId, MemberDTO memberDTO) {
        MembershipPlan plan = planRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Membership plan with id " + planId + " not found"));

        long activeMembersAmount = memberRepository.countByMembershipPlanIdAndStatus(planId, MemberStatus.ACTIVE);
        if (activeMembersAmount >= plan.getMaxMembers()) {
            throw new BusinessLogicException("Membership plan already has maximum amount of members (" + plan.getMaxMembers() + ").");
        }

        Member member = memberMapper.toEntity(memberDTO);
        member.setMembershipPlan(plan);

        // Setting date and ACTIVE status automatically
        member.setStartDate(LocalDate.now());
        member.setStatus(MemberStatus.ACTIVE);

        Member savedMember = memberRepository.save(member);
        return memberMapper.toDTO(savedMember);
    }

    @Transactional(readOnly = true)
    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(memberMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void cancelMembership(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // Changing status to CANCELLED
        member.setStatus(MemberStatus.CANCELLED);
        memberRepository.save(member);
    }
}