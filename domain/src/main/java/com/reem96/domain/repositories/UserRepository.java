package com.reem96.domain.repositories;

// Created by Shorasul Sh. on 29.07.2020

import com.reem96.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameIgnoreCase(String username);

}
