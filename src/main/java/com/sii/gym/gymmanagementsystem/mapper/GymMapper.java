package com.sii.gym.gymmanagementsystem.mapper;

import com.sii.gym.gymmanagementsystem.dto.GymDTO;
import com.sii.gym.gymmanagementsystem.model.Gym;
import org.springframework.stereotype.Component;

@Component
public class GymMapper {

    public GymDTO toDTO(Gym gym) {
        return GymDTO.builder()
                .id(gym.getId())
                .name(gym.getName())
                .address(gym.getAddress())
                .phoneNumber(gym.getPhoneNumber())
                .build();
    }

    public Gym toEntity(GymDTO dto) {
        return Gym.builder()
                .name(dto.name())
                .address(dto.address())
                .phoneNumber(dto.phoneNumber())
                .build();
    }
}