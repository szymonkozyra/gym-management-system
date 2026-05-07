package com.sii.gym.gymmanagementsystem.service;

import com.sii.gym.gymmanagementsystem.dto.GymDTO;
import com.sii.gym.gymmanagementsystem.exception.BusinessLogicException;
import com.sii.gym.gymmanagementsystem.mapper.GymMapper;
import com.sii.gym.gymmanagementsystem.model.Gym;
import com.sii.gym.gymmanagementsystem.repository.GymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
}
