package com.reem96.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.reem96.domain.dto.WaterDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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

    @Test
    public void getAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/water/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(MvcResult::getResponse)
                .andExpect(content().string(containsString("id")));
    }

    @Test
    void getByUserId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/water/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createWater() throws Exception {
        WaterDto waterDto = new WaterDto();
        waterDto.setAmount(AMOUNT);
        waterDto.setUserId(USER_ID);
        waterDto.setIsColdWater(IS_COLD_WATER);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String request = objectWriter.writeValueAsString(waterDto);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/water/").contentType(MediaType.APPLICATION_JSON).content(request)).andExpect(status().isCreated());

    }
}