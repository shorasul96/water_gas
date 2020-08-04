package com.reem96.domain.dto;

// Created by Shorasul Sh. on 29.07.2020

import com.fasterxml.jackson.annotation.JsonInclude;
import com.reem96.domain.validation.gas.CheckUserForGas;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@CheckUserForGas
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GasDto implements Serializable {

    private Long id;
    @NotNull(message = "Please, fill the field amount")
    @Min(value = 1, message = "Field amount, must be positive number")
    private Long amount;

    @NotNull(message = "Please, fill the field userId")
    private Long userId;
    private UserDto userDto;

}
