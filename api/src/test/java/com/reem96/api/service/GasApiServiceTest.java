package com.reem96.api.service;

import com.reem96.domain.dto.GasDto;
import com.reem96.domain.entities.GasEntity;
import com.reem96.domain.repositories.GasRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
class GasApiServiceTest {

    private static final Long USER_ID = 1L;
    private static final Long AMOUNT = 70L;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GasApiService gasApiService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        GasDto dto = this.getFullDto();
        when(gasApiService.getAll()).thenReturn(Stream.of(dto).collect(Collectors.toList()));

        List<GasDto> all = gasApiService.getAll();
        Assert.assertEquals(Stream.of(dto).collect(Collectors.toList()), all);

    }

    @Test
    void testFindByUserId() {
        GasDto dto = this.getFullDto();
        when(gasApiService.findByUserId(anyLong())).thenReturn(Stream.of(dto).collect(Collectors.toList()));

        List<GasDto> gasDtoList = gasApiService.findByUserId(anyLong());
        Assert.assertEquals(Stream.of(dto).collect(Collectors.toList()), gasDtoList);
    }

    @Test
    void testSaveGas() {
        GasDto dto = this.getFullDto();
        when(gasApiService.saveGas(dto)).thenReturn(dto);

        GasDto gasDto = gasApiService.saveGas(dto);
        Assert.assertEquals(gasDto, dto);
    }

    @Test
    void testTotalGasByUserId() {
        GasDto dto = this.getFullDto();
        when(gasApiService.totalGasByUserId(anyLong())).thenReturn(dto.getAmount());

        Long result = gasApiService.totalGasByUserId(anyLong());
        Assertions.assertEquals(dto.getAmount(), result);
    }

    public GasDto getFullDto() {
        GasDto dto = new GasDto();
        dto.setUserId(USER_ID);
        dto.setAmount(AMOUNT);
        return dto;
    }
}
