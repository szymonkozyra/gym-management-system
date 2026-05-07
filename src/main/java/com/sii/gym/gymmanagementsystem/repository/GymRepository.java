package com.sii.gym.gymmanagementsystem.repository;

import com.sii.gym.gymmanagementsystem.model.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {
    // Additional verification of name uniqueness
    boolean existsByName(String name);
}