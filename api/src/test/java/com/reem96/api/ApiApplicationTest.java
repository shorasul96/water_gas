package com.reem96.api;

import com.reem96.domain.repositories.GasRepository;
import com.reem96.domain.repositories.UserRepository;
import com.reem96.domain.repositories.WaterRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


// Created by Shorasul Sh. on 29.07.2020

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ApiApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class ApiApplicationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserRepository userRepository;
    @MockBean
    GasRepository gasRepository;
    @MockBean
    WaterRepository waterRepository;

    @Test
    void contextLoads() throws Exception {

        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        when(gasRepository.findAll()).thenReturn(Collections.emptyList());
        when(waterRepository.findAll()).thenReturn(Collections.emptyList());

        MvcResult userResult = mockMvc
                .perform(get("/api/v1/users/")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        MvcResult gasResult = mockMvc
                .perform(get("/api/v1/gas/")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        MvcResult waterResult = mockMvc
                .perform(get("/api/v1/water/")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(userResult.getResponse());
        System.out.println(gasResult.getResponse());
        System.out.println(waterResult.getResponse());
    }
}