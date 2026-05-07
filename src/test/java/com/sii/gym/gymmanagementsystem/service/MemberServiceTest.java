package com.sii.gym.gymmanagementsystem.service;

import com.sii.gym.gymmanagementsystem.dto.MemberDTO;
import com.sii.gym.gymmanagementsystem.exception.BusinessLogicException;
import com.sii.gym.gymmanagementsystem.exception.BusinessLogicException;
import com.sii.gym.gymmanagementsystem.mapper.MemberMapper;
import com.sii.gym.gymmanagementsystem.model.Member;
import com.sii.gym.gymmanagementsystem.model.MemberStatus;
import com.sii.gym.gymmanagementsystem.model.MembershipPlan;
import com.sii.gym.gymmanagementsystem.repository.MemberRepository;
import com.sii.gym.gymmanagementsystem.repository.MembershipPlanRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private MembershipPlanRepository planRepository;
    @Mock
    private MemberMapper memberMapper;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("Should register member successfully when capacity is available")
    void shouldRegisterMemberSuccessfully() {
        // Given
        Long planId = 1L;
        MembershipPlan plan = MembershipPlan.builder()
                .id(planId)
                .maxMembers(10)
                .build();
        MemberDTO inputDto = MemberDTO.builder().fullName("John Doe").email("john@example.com").build();
        Member memberEntity = new Member();

        when(planRepository.findById(planId)).thenReturn(Optional.of(plan));
        when(memberRepository.countByMembershipPlanIdAndStatus(planId, MemberStatus.ACTIVE)).thenReturn(5L);
        when(memberMapper.toEntity(any())).thenReturn(memberEntity);
        when(memberRepository.save(any())).thenReturn(memberEntity);
        when(memberMapper.toDTO(any())).thenReturn(inputDto);

        // When
        MemberDTO result = memberService.registerMember(planId, inputDto);

        // Then
        assertNotNull(result);
        verify(memberRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Should throw BusinessException when plan capacity is reached")
    void shouldThrowExceptionWhenCapacityReached() {
        // Given
        Long planId = 1L;
        MembershipPlan plan = MembershipPlan.builder()
                .id(planId)
                .maxMembers(5)
                .build();

        when(planRepository.findById(planId)).thenReturn(Optional.of(plan));

        when(memberRepository.countByMembershipPlanIdAndStatus(planId, MemberStatus.ACTIVE)).thenReturn(5L);

        // When & Then
        BusinessLogicException exception = assertThrows(BusinessLogicException.class, () ->
                memberService.registerMember(planId, MemberDTO.builder().build())
        );

        assertTrue(exception.getMessage().contains("maximum capacity"));
        verify(memberRepository, never()).save(any());
    }
}