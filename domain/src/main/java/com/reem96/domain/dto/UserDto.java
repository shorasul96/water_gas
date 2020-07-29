package com.reem96.domain.dto;

// Created by Shorasul Sh. on 29.07.2020

import com.fasterxml.jackson.annotation.JsonInclude;
import com.reem96.domain.validation.user.CheckUniqueUsername;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@CheckUniqueUsername
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {

    private Long id;

    @NotEmpty(message = "Пожалуйста, введите аккоунт название пользователя")
    private String username;

    private Long totalGas;
    private Long totalHotWater;
    private Long totalColdWater;

    private Boolean isActive;

}
