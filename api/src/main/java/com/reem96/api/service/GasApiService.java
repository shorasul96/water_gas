package com.reem96.api.service;

// Created by Shorasul Sh. on 29.07.2020

import com.reem96.domain.dto.GasDto;
import com.reem96.domain.entities.GasEntity;
import com.reem96.domain.repositories.GasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GasApiService {

    private final GasRepository gasRepository;


    public List<GasDto> getAll() {
        return gasRepository.findAll().stream().map(GasEntity::getDto).collect(Collectors.toList());
    }

    public List<GasDto> findByUserId(Long id) {
        return gasRepository.findAllByUserId(id).stream().map(GasEntity::getDto).collect(Collectors.toList());
    }

    public void saveGas(GasDto gasDto) {
        GasEntity gasEntity = new GasEntity();
        BeanUtils.copyProperties(gasDto, gasEntity);
        gasEntity.setCreatedDate(LocalDateTime.now());
        gasRepository.save(gasEntity);
    }
}
