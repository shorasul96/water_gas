package com.reem96.api.service;

import com.reem96.domain.dto.GasDto;
import com.reem96.domain.dto.WaterDto;
import com.reem96.domain.entities.WaterEntity;
import com.reem96.domain.repositories.WaterRepository;
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
class WaterApiServiceTest {

    private static final Long USER_ID = 1L;
    private static final Long AMOUNT = 70L;
    private static final boolean IS_COLD_WATER = true;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WaterApiService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        WaterDto dto = this.getFullDto();
        when(service.getAll()).thenReturn(Stream.of(dto).collect(Collectors.toList()));

        List<WaterDto> all = service.getAll();
        Assert.assertEquals(Stream.of(dto).collect(Collectors.toList()), all);
    }

    @Test
    void testFindByUserId() {
        WaterDto dto = this.getFullDto();
        when(service.findByUserId(anyLong())).thenReturn(Stream.of(dto).collect(Collectors.toList()));


        List<WaterDto> result = service.findByUserId(anyLong());
        Assertions.assertEquals(Stream.of(dto).collect(Collectors.toList()), result);
    }

    @Test
    void testSaveWater() {
        WaterDto dto = this.getFullDto();
        when(service.saveWater(dto)).thenReturn(dto);

        WaterDto gasDto = service.saveWater(dto);
        Assert.assertEquals(gasDto, dto);
    }

    @Test
    void testTotalColdWaterByUserId() {
        WaterDto dto = this.getFullDto();
        when(service.totalColdWaterByUserId(anyLong())).thenReturn(dto.getAmount());

        Long result = service.totalColdWaterByUserId(anyLong());
        Assertions.assertEquals(dto.getAmount(), result);
    }

    @Test
    void testTotalHotWaterByUserId() {
        WaterDto dto = this.getFullDto();
        when(service.totalHotWaterByUserId(anyLong())).thenReturn(dto.getAmount());

        Long result = service.totalHotWaterByUserId(anyLong());
        Assertions.assertEquals(dto.getAmount(), result);
    }

    public WaterDto getFullDto() {
        WaterDto dto = new WaterDto();
        dto.setUserId(USER_ID);
        dto.setAmount(AMOUNT);
        dto.setIsColdWater(IS_COLD_WATER);
        return dto;
    }
}

