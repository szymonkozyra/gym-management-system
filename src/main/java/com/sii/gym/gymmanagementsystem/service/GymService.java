package com.sii.gym.gymmanagementsystem.service;

import com.sii.gym.gymmanagementsystem.dto.GymDTO;
import com.sii.gym.gymmanagementsystem.dto.RevenueReportDTO;
import com.sii.gym.gymmanagementsystem.exception.BusinessLogicException;
import com.sii.gym.gymmanagementsystem.mapper.GymMapper;
import com.sii.gym.gymmanagementsystem.model.Gym;
import com.sii.gym.gymmanagementsystem.model.MemberStatus;
import com.sii.gym.gymmanagementsystem.model.MembershipPlan;
import com.sii.gym.gymmanagementsystem.repository.GymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Dependency Injection
public class GymService {
    private final GymRepository gymRepository;
    private final GymMapper gymMapper;

    @Transactional
    public GymDTO create(GymDTO gymDTO) {
        if (gymRepository.existsByName(gymDTO.name())) {
            throw new BusinessLogicException("Gym with name '" + gymDTO.name() + "' already exists.");
        }
        Gym gym = gymMapper.toEntity(gymDTO);
        Gym savedGym = gymRepository.save(gym);
        return gymMapper.toDTO(savedGym);
    }

    @Transactional(readOnly = true)
    public List<GymDTO> getAllGyms() {
        return gymRepository.findAll().stream()
                .map(gymMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RevenueReportDTO> getRevenueReport() {
        return gymRepository.findAll().stream()
                .flatMap(gym -> calculateRevenueByCurrency(gym).entrySet().stream()
                        .map(entry -> new RevenueReportDTO(gym.getName(), entry.getValue(), entry.getKey())))
                .collect(Collectors.toList());
    }

    private Map<String, BigDecimal> calculateRevenueByCurrency(Gym gym) {
        return gym.getMembershipPlans().stream()
                .collect(Collectors.groupingBy(
                        // Grouping revenue by currency
                        MembershipPlan::getCurrency,
                        Collectors.mapping(plan -> {
                            long activeMembers = plan.getMembers().stream()
                                    .filter(m -> m.getStatus() == MemberStatus.ACTIVE)
                                    .count();
                            return plan.getPrice().multiply(BigDecimal.valueOf(activeMembers));
                        }, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
                ));
    }
}
