package com.reem96.domain.entities;

// Created by Shorasul Sh. on 29.07.2020

import com.reem96.domain.constant.TableNames;
import com.reem96.domain.dto.UserDto;
import com.reem96.domain.entities.base.ModifierEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = TableNames.USER_ENTITY)
public class UserEntity extends ModifierEntity {

    @Column(name = "username", length = 64, unique = true)
    private String username;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive = Boolean.TRUE;

    public UserDto getDto(){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(this,userDto);
        return userDto;
    }

}
