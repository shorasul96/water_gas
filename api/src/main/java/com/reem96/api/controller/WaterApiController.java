package com.reem96.api.controller;

// Created by Shorasul Sh. on 29.07.2020

import com.reem96.api.service.UserApiService;
import com.reem96.api.service.WaterApiService;
import com.reem96.domain.dto.WaterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/water")
@RequiredArgsConstructor
public class WaterApiController {

    private final WaterApiService waterApiService;
    private final UserApiService userApiService;

    @GetMapping("/")
    List<WaterDto> getAll() {
        return waterApiService.getAll();
    }

    @GetMapping("/{id}")
    List<WaterDto> getByUserId(@PathVariable("id") Long id) {
        return waterApiService.findByUserId(id);
    }

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<String> createWater(@RequestBody WaterDto waterDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Unsuccess!");

        } else {
            if (userApiService.checkUser(waterDto.getUserId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User id not found");
            }
            waterApiService.saveWater(waterDto);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Success!");
    }
}
