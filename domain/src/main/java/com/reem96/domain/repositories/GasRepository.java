package com.reem96.domain.repositories;

// Created by Shorasul Sh. on 29.07.2020

import com.reem96.domain.entities.GasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface GasRepository extends JpaRepository<GasEntity, Long> {

    @Query(value = "SELECT * FROM public.gas WHERE user_id = :id ", nativeQuery = true)
    List<GasEntity> findAllByUserId(@Param("id") Long id);

}
