package com.reem96.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reem96.api.service.GasApiService;
import com.reem96.domain.dto.GasDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


// Created by Shorasul Sh. on 29.07.2020

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class GasApiControllerTest {

    private static final Long USER_ID = 1L;
    private static final Long AMOUNT = 70L;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GasApiService service;

    @Test
    void shouldGetAll() throws Exception {
        GasDto dto = new GasDto();
        dto.setUserId(USER_ID);
        dto.setAmount(AMOUNT);

        when(service.getAll()).thenReturn(Stream.of(dto).collect(Collectors.toList()));

        mvc.perform(get("/api/v1/gas/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].userId").value(USER_ID))
                .andExpect(jsonPath("$[0].amount").value(AMOUNT))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetByUserId() throws Exception {
        GasDto dto = new GasDto();
        dto.setUserId(USER_ID);
        dto.setAmount(AMOUNT);

        when(service.findByUserId(anyLong())).thenReturn(Stream.of(dto).collect(Collectors.toList()));

        mvc.perform(get("/api/v1/gas/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].userId").value(USER_ID))
                .andExpect(jsonPath("$[0].amount").value(AMOUNT))
                .andExpect(status().isOk());
    }
    @Test
    void shouldNotGetByUser_IdNotFound() throws Exception {
        GasDto dto = new GasDto();
        dto.setUserId(0L);
        dto.setAmount(AMOUNT);

        when(service.findByUserId(anyLong())).thenReturn(null);
        mvc.perform(get("/api/v1/gas/0")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateGas() throws Exception {
        GasDto dto = new GasDto();
        dto.setAmount(AMOUNT);
        dto.setUserId(USER_ID);

        mvc.perform(post("/api/v1/gas/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }
    @Test
    void shouldNotCreateGas_InvalidUserID() throws Exception {
        GasDto dto = new GasDto();
        dto.setAmount(AMOUNT);
        dto.setUserId(null);

        mvc.perform(post("/api/v1/gas/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void shouldNotCreateGas_InvalidAmount() throws Exception {
        GasDto dto = new GasDto();
        dto.setAmount(null);
        dto.setUserId(null);

        mvc.perform(post("/api/v1/gas/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}