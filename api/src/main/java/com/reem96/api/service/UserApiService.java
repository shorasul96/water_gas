package com.reem96.api.service;

// Created by Shorasul Sh. on 29.07.2020

import com.reem96.domain.dto.GasDto;
import com.reem96.domain.dto.UserDto;
import com.reem96.domain.dto.WaterDto;
import com.reem96.domain.entities.GasEntity;
import com.reem96.domain.entities.UserEntity;
import com.reem96.domain.entities.WaterEntity;
import com.reem96.domain.repositories.GasRepository;
import com.reem96.domain.repositories.UserRepository;
import com.reem96.domain.repositories.WaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserApiService {

    private final UserRepository userRepository;
    private final GasRepository gasRepository;
    private final WaterRepository waterRepository;


    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserEntity::getDto).collect(Collectors.toList());
    }

    public UserDto getById(Long id) {
        Optional<UserEntity> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            UserDto dto = optional.get().getDto();
            dto.setTotalGas(this.totalGasByUserId(dto.getId()));
            dto.setTotalColdWater(this.totalColdWaterByUserId(dto.getId()));
            dto.setTotalHotWater(this.totalHotWaterByUserId(dto.getId()));
            return dto;
        }
        return null;
    }

    public void saveUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setIsActive(true);
        userEntity.setCreatedDate(LocalDateTime.now());
        userRepository.save(userEntity);
    }

    public boolean checkUser(Long userId) {
        if (userId == null || userId < 1) {
            return true;
        }
        return !userRepository.findById(userId).isPresent();
    }

    // gas repo methods -------------------------
    public Long totalGasByUserId(Long userId) {
        return gasRepository.findAllByUserId(userId).stream().mapToLong(GasEntity::getAmount).sum();
    }


    // water repo methods -------------------------
    public Long totalColdWaterByUserId(Long userId) {
        return waterRepository.findAllByUserId(userId).stream().filter(WaterEntity::getIsColdWater).mapToLong(WaterEntity::getAmount).sum();
    }

    public Long totalHotWaterByUserId(Long userId) {
        return waterRepository.findAllByUserId(userId).stream().filter(waterEntity -> !waterEntity.getIsColdWater()).mapToLong(WaterEntity::getAmount).sum();
    }
}
