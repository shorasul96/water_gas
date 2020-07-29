package com.reem96.domain.repositories;

// Created by Shorasul Sh. on 29.07.2020

import com.reem96.domain.entities.WaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface WaterRepository extends JpaRepository<WaterEntity, Long> {

    @Query(value = "SELECT * FROM public.water WHERE isColdWater = :typeCold ", nativeQuery = true)
    List<WaterEntity> findAllByWaterType(@Param("typeCold") Boolean typeCold);

    @Query(value = "SELECT * FROM public.water WHERE user_id = :id ", nativeQuery = true)
    List<WaterEntity> findAllByUserId(@Param("id") Long id);
}
