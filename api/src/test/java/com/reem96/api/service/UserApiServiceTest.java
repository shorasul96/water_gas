package com.reem96.api.service;

import com.reem96.domain.dto.UserDto;
import com.reem96.domain.entities.UserEntity;
import com.reem96.domain.repositories.UserRepository;
import org.apache.catalina.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;


// Created by Shorasul Sh. on 30.07.2020
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserApiServiceTest {

    private static final Long USER_ID = 1L;
    private static final String USERNAME = "ADMIN";
    private static final boolean IS_ACTIVE = true;


    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserApiService userApiService;
    @MockBean
    GasApiService gasApiService;
    @MockBean
    WaterApiService waterApiService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll()  {
        UserDto dto = this.getFullTestDto();
        when(userApiService.getAll()).thenReturn(Stream.of(dto).collect(Collectors.toList()));
        List<UserDto> all = userApiService.getAll();
        Assert.assertEquals(Stream.of(dto).collect(Collectors.toList()), all);

    }

    @Test
    void testGetById() {
        UserDto dto = this.getFullTestDto();
        when(userApiService.getById(USER_ID)).thenReturn(dto);
        UserDto result = userApiService.getById(USER_ID);
        Assertions.assertEquals(dto, result);
    }

    @Test
    void testSaveUser() {
        UserDto dto = this.getTestDto();
        when(userApiService.saveUser(dto)).thenReturn(dto);
        Assert.assertEquals(dto, userApiService.saveUser(dto));
    }

    @Test
    void testCheckUser() {
       when(userApiService.checkUser(anyLong())).thenReturn(false);
        Assert.assertFalse(userApiService.checkUser(1L));

    }


    public UserDto getFullTestDto(){
        UserDto dto = new UserDto();
        dto.setId(USER_ID);
        dto.setUsername(USERNAME);
        dto.setIsActive(IS_ACTIVE);
        dto.setTotalGas(gasApiService.totalGasByUserId(USER_ID));
        dto.setTotalColdWater(waterApiService.totalColdWaterByUserId(USER_ID));
        dto.setTotalHotWater(waterApiService.totalHotWaterByUserId(USER_ID));
        return dto;
    }

    public UserDto getTestDto(){
        UserDto dto = new UserDto();
        dto.setUsername(USERNAME);
        dto.setIsActive(IS_ACTIVE);
        return dto;
    }
}

