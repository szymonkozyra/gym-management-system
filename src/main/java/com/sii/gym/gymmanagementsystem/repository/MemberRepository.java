package com.sii.gym.gymmanagementsystem.repository;

import com.sii.gym.gymmanagementsystem.model.Member;
import com.sii.gym.gymmanagementsystem.model.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // Liczenie aktywnych członków w danym planie żeby nie przekroczyć maxMembers
    long countByMembershipPlanIdAndStatus(Long membershipPlanId, MemberStatus status);

    boolean existsByEmail(String email);
}