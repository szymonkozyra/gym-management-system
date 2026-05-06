package com.sii.gym.gymmanagementsystem.repository;

import com.sii.gym.gymmanagementsystem.model.MembershipPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipPlanRepository extends JpaRepository<MembershipPlan, Long> {
    // Dla Endpointu Pobierania wszystkich planów dla danej siłowni
    List<MembershipPlan> findByGymId(Long gymId);

    boolean existsByNameAndGymId(String name, Long gymId);
}