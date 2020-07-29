package com.reem96.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.reem96.domain.dto.GasDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// Created by Shorasul Sh. on 29.07.2020

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class GasApiControllerTest {

    private static final Long USER_ID = 1L;
    private static final Long AMOUNT = 70L;

    @Autowired
    private MockMvc mvc;

    @Test
    void getAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/gas/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(MvcResult::getResponse);
    }

    @Test
    void getByUserId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/gas/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createGas() throws Exception {
        GasDto dto = new GasDto();
        dto.setAmount(AMOUNT);
        dto.setUserId(USER_ID);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String request = objectWriter.writeValueAsString(dto);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/gas/").contentType(MediaType.APPLICATION_JSON).content(request)).andExpect(status().isCreated());
    }
}