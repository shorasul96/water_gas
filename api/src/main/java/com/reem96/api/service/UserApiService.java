package com.reem96.api.service;

// Created by Shorasul Sh. on 29.07.2020

import com.reem96.domain.dto.UserDto;
import com.reem96.domain.entities.UserEntity;
import com.reem96.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserApiService {

    private final UserRepository userRepository;
    private final GasApiService gasApiService;
    private final WaterApiService waterApiService;


    public List<UserDto> getAll() {
        return userRepository
                .findAll().stream()
                .map(UserEntity::getDto)
                .collect(Collectors.toList());
    }

    public UserDto getById(Long id) {
        Optional<UserEntity> optional = userRepository.findById(id);
        if (optional.isPresent()){
            UserDto dto = optional.get().getDto();
            dto.setTotalGas(gasApiService.totalGasByUserId(id));
            dto.setTotalColdWater(waterApiService.totalColdWaterByUserId(id));
            dto.setTotalHotWater(waterApiService.totalHotWaterByUserId(id));
            return dto;
        }
        return new UserDto();
    }

    public UserDto saveUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setIsActive(true);
        userEntity.setCreatedDate(LocalDateTime.now());
        userRepository.save(userEntity);
        return userDto;
    }
}
