package com.sii.gym.gymmanagementsystem.controller;

import com.sii.gym.gymmanagementsystem.dto.GymDTO;
import com.sii.gym.gymmanagementsystem.service.GymService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gyms")
@RequiredArgsConstructor
@Tag(name = "Gym Controller", description = "Endpoints for managing gyms")
public class GymController {
    private final GymService gymService;

    @PostMapping
    @Operation(summary = "Create a new gym")
    public ResponseEntity<GymDTO> createGym(@Valid @RequestBody GymDTO gymDTO) {
        return new ResponseEntity<>(gymService.create(gymDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "List all gyms")
    public ResponseEntity<List<GymDTO>> getAllGyms() {
        return new ResponseEntity<>(gymService.getAllGyms(), HttpStatus.OK);
    }
}