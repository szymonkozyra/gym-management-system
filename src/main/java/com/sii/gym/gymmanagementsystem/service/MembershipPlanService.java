package com.sii.gym.gymmanagementsystem.service;

import com.sii.gym.gymmanagementsystem.dto.MembershipPlanDTO;
import com.sii.gym.gymmanagementsystem.mapper.MembershipPlanMapper;
import com.sii.gym.gymmanagementsystem.model.Gym;
import com.sii.gym.gymmanagementsystem.model.MembershipPlan;
import com.sii.gym.gymmanagementsystem.repository.GymRepository;
import com.sii.gym.gymmanagementsystem.repository.MembershipPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MembershipPlanService {
    private final MembershipPlanRepository planRepository;
    private final GymRepository gymRepository;
    private final MembershipPlanMapper planMapper;

    @Transactional
    public MembershipPlanDTO createPlan(Long gymId, MembershipPlanDTO planDTO) {
        Gym gym = gymRepository.findById(gymId)
                .orElseThrow(() -> new RuntimeException("Gym with id: " + gymId + " not found"));

        MembershipPlan plan = planMapper.toEntity(planDTO);
        plan.setGym(gym);

        MembershipPlan savedPlan = planRepository.save(plan);
        return planMapper.toDTO(savedPlan);
    }

    @Transactional(readOnly = true)
    public List<MembershipPlanDTO> getPlansByGym(Long gymId) {
        return planRepository.findByGymId(gymId).stream()
                .map(planMapper::toDTO)
                .collect(Collectors.toList());
    }
}