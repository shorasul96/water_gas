package com.reem96.api.service;

import com.reem96.domain.dto.UserDto;
import com.reem96.domain.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;


// Created by Shorasul Sh. on 29.07.2020

public class UserApiServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    GasApiService gasApiService;
    @Mock
    WaterApiService waterApiService;
    @InjectMocks
    UserApiService userApiService;

    private static final Long ID = 1L;
    private static final String USERNAME = "Shorasul";
    private static final boolean IS_ACTIVE = true;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        UserDto dto = new UserDto();
        dto.setId(ID);
        dto.setUsername(USERNAME);
        dto.setIsActive(IS_ACTIVE);
        List<UserDto> result = userApiService.getAll();
        Assert.assertEquals(Stream.of(dto).collect(Collectors.toList()), result);
    }

    @Test
    public void testGetById() throws Exception {
        when(gasApiService.totalGasByUserId(anyLong())).thenReturn(Long.valueOf(1));
        when(waterApiService.totalColdWaterByUserId(anyLong())).thenReturn(Long.valueOf(1));
        when(waterApiService.totalHotWaterByUserId(anyLong())).thenReturn(Long.valueOf(1));

        UserDto result = userApiService.getById(Long.valueOf(1));
        Assert.assertEquals(new UserDto(), result);
    }

    @Test
    public void testSaveUser() throws Exception {
        userApiService.saveUser(new UserDto());
    }

    @Test
    public void testCheckUser() throws Exception {
        boolean result = userApiService.checkUser(1L);
        Assert.assertEquals(true, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme