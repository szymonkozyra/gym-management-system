package com.sii.gym.gymmanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sii.gym.gymmanagementsystem.dto.GymDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class GymControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAndReturnGym() throws Exception {
        GymDTO gymDTO = GymDTO.builder()
                .name("Test Gym Unique")
                .address("123 Test St")
                .phoneNumber("123456789")
                .build();

        // Test POST (Create)
        mockMvc.perform(post("/api/gyms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(gymDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Gym Unique"));

        // Test GET (List all)
        mockMvc.perform(get("/api/gyms"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Test Gym Unique"));
    }
}