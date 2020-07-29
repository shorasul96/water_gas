package com.reem96.api.service;

import com.reem96.domain.dto.GasDto;
import com.reem96.domain.entities.GasEntity;
import com.reem96.domain.repositories.GasRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


// Created by Shorasul Sh. on 29.07.2020

class GasApiServiceTest {
    @Mock
    GasRepository gasRepository;

    @MockBean
    GasApiService gasApiService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        List<GasDto> result = gasApiService.getAll();
        Assertions.assertEquals(Arrays.<GasDto>asList(new GasDto()), result);
    }

    @Test
    void testFindByUserId() {
        when(gasRepository.findAllByUserId(anyLong())).thenReturn(Arrays.<GasEntity>asList(new GasEntity()));

        List<GasDto> result = gasApiService.findByUserId(1L);
        Assertions.assertTrue(result.size() >= 1);
    }

    @Test
    void testSaveGas() {
        gasApiService.saveGas(new GasDto());
    }

    @Test
    void testTotalGasByUserId() {
        GasDto dto = new GasDto();
        dto.setId(1L);
        dto.setUserId(1L);
        dto.setAmount(60L);


        Long result = gasApiService.totalGasByUserId(1L);
        Assertions.assertTrue(result >= 1L);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme