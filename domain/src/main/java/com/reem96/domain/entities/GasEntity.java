package com.reem96.domain.entities;

import com.reem96.domain.constant.TableNames;
import com.reem96.domain.dto.GasDto;
import com.reem96.domain.dto.WaterDto;
import com.reem96.domain.entities.base.ModifierEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

// Created by Shorasul Sh. on 29.07.2020


@Getter
@Setter
@Entity
@Table(name = TableNames.GAS_ENTITY)
public class GasEntity extends ModifierEntity {


    @Column(name = "amount")
    private Long amount;


    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity userEntity;

    public GasDto getDto(){
        GasDto gasDto = new GasDto();
        BeanUtils.copyProperties(this,gasDto);
        return gasDto;
    }
}
