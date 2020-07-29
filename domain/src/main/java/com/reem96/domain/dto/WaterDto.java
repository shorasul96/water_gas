package com.reem96.domain.dto;

// Created by Shorasul Sh. on 29.07.2020

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WaterDto implements Serializable {

    private Long id;
    @NotNull
    private Long amount;
    @NotNull
    private Boolean isColdWater;

    @NotNull
    private Long userId;
    private UserDto userDto;

}
