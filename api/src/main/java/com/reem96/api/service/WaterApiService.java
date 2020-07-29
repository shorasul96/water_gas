package com.reem96.api.service;

// Created by Shorasul Sh. on 29.07.2020

import com.reem96.domain.dto.GasDto;
import com.reem96.domain.dto.WaterDto;
import com.reem96.domain.entities.GasEntity;
import com.reem96.domain.entities.WaterEntity;
import com.reem96.domain.repositories.WaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class WaterApiService {

    private final WaterRepository waterRepository;


    public List<WaterDto> getAll() {
        return waterRepository.findAll().stream().map(WaterEntity::getDto).collect(Collectors.toList());
    }

    public List<WaterDto> findByUserId(Long id) {
        return waterRepository.findAllByUserId(id).stream().map(WaterEntity::getDto).collect(Collectors.toList());
    }

    public void saveWater(WaterDto waterDto) {
        WaterEntity waterEntity = new WaterEntity();
        BeanUtils.copyProperties(waterDto, waterEntity);
        if (waterDto.getIsColdWater() == null)
            waterEntity.setIsColdWater(true);
        waterEntity.setCreatedDate(LocalDateTime.now());
        waterRepository.save(waterEntity);
    }
}
