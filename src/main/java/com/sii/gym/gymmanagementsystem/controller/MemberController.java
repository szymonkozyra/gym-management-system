package com.sii.gym.gymmanagementsystem.controller;

import com.sii.gym.gymmanagementsystem.dto.MemberDTO;
import com.sii.gym.gymmanagementsystem.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Member Controller", description = "Endpoints for managing gym members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/plans/{planId}/members")
    @Operation(summary = "Register a new member to a given membership plan")
    public ResponseEntity<MemberDTO> registerMember(@PathVariable Long planId, @Valid @RequestBody MemberDTO memberDTO) {
        return new ResponseEntity<>(memberService.registerMember(planId, memberDTO), HttpStatus.CREATED);
    }

    @GetMapping("/members")
    @Operation(summary = "List all members with their plan details")
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @PatchMapping("/members/{memberId}/cancelMembership")
    @Operation(summary = "Cancel a membership of a given member")
    public ResponseEntity<Void> cancelMembership(@PathVariable Long memberId) {
        memberService.cancelMembership(memberId);
        return ResponseEntity.noContent().build();
    }
}