package com.reem96.domain.entities;

// Created by Shorasul Sh. on 29.07.2020

import com.reem96.domain.constant.TableNames;
import com.reem96.domain.dto.UserDto;
import com.reem96.domain.dto.WaterDto;
import com.reem96.domain.entities.base.ModifierEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = TableNames.WATER_ENTITY)
public class WaterEntity extends ModifierEntity {

    @Column(name = "is_cold_water", columnDefinition = "boolean default true")
    private Boolean isColdWater;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity userEntity;

    public WaterDto getDto(){
        WaterDto waterDto = new WaterDto();
        BeanUtils.copyProperties(this,waterDto);
        return waterDto;
    }
}
