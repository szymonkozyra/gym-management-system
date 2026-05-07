package com.sii.gym.gymmanagementsystem.controller;

import com.sii.gym.gymmanagementsystem.dto.MembershipPlanDTO;
import com.sii.gym.gymmanagementsystem.service.MembershipPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gyms/{gymId}/plans")
@RequiredArgsConstructor
@Tag(name = "Membership Plan Controller", description = "Endpoints for gym membership plans")
public class MembershipPlanController {
    private final MembershipPlanService membershipPlanService;

    @PostMapping
    @Operation(summary = "Create a new membership plan for a given gym")
    public ResponseEntity<MembershipPlanDTO> createPlan(@PathVariable Long gymId, @Valid @RequestBody MembershipPlanDTO membershipPlanDTO) {
        return new ResponseEntity<>(membershipPlanService.createPlan(gymId, membershipPlanDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "List all membership plans for a given gym")
    public ResponseEntity<List<MembershipPlanDTO>> getPlansByGym(@PathVariable Long gymId) {
        return ResponseEntity.ok(membershipPlanService.getPlansByGym(gymId));
    }
}
