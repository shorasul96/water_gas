package com.reem96.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reem96.api.service.UserApiService;
import com.reem96.domain.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// Created by Shorasul Sh. on 29.07.2020

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserApiControllerTest {

    private static final String USERNAME = "ADMIN99";
    private static final boolean IS_ACTIVE = true;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserApiService service;

    @Test
    void getAll() throws Exception {
        mvc.perform(get("/api/v1/users/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getUserById() throws Exception {
        UserDto dto = new UserDto();
        dto.setUsername(USERNAME);
        dto.setIsActive(IS_ACTIVE);

        when(service.getById(anyLong())).thenReturn(dto);

        mvc.perform(get("/api/v1/users/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value(USERNAME))
                .andExpect(jsonPath("$.isActive").value(IS_ACTIVE))
                .andExpect(status().isOk());
    }

    @Test
    void createUser() throws Exception {

        UserDto dto = new UserDto();
        dto.setUsername(USERNAME);
        dto.setIsActive(IS_ACTIVE);

        mvc.perform(post("/api/v1/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isCreated());

    }
}