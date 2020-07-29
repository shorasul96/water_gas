package com.reem96.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reem96.api.service.WaterApiService;
import com.reem96.domain.dto.WaterDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

// Created by Shorasul Sh. on 29.07.2020

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class WaterApiControllerTest {

    private static final Long USER_ID = 1L;
    private static final Long AMOUNT = 70L;
    private static final boolean IS_COLD_WATER = true;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WaterApiService service;

    @Test
    public void getAll() throws Exception {
        WaterDto dto = new WaterDto();
        dto.setAmount(AMOUNT);
        dto.setUserId(USER_ID);
        dto.setIsColdWater(IS_COLD_WATER);

        when(service.getAll())
                .thenReturn(Stream.of(dto).collect(Collectors.toList()));

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/water/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].userId").value(USER_ID))
                .andExpect(jsonPath("$[0].amount").value(AMOUNT))
                .andExpect(status().isOk());
    }

    @Test
    void getByUserId() throws Exception {

        WaterDto dto = new WaterDto();
        dto.setAmount(AMOUNT);
        dto.setUserId(USER_ID);
        dto.setIsColdWater(IS_COLD_WATER);

        when(service.findByUserId(anyLong()))
                .thenReturn(Stream.of(dto).collect(Collectors.toList()));

        mvc.perform(get("/api/v1/water/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].userId").value(USER_ID))
                .andExpect(jsonPath("$[0].amount").value(AMOUNT))
                .andExpect(status().isOk());
    }

    @Test
    void createWater() throws Exception {
        WaterDto waterDto = new WaterDto();
        waterDto.setAmount(AMOUNT);
        waterDto.setUserId(USER_ID);
        waterDto.setIsColdWater(IS_COLD_WATER);

        mvc.perform(post("/api/v1/water/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(waterDto)))
                .andExpect(status().isCreated());

    }
}