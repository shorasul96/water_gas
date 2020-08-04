package com.reem96.api.controller;

// Created by Shorasul Sh. on 29.07.2020

import com.reem96.api.service.UserApiService;
import com.reem96.api.service.WaterApiService;
import com.reem96.domain.dto.UserDto;
import com.reem96.domain.dto.WaterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    ResponseEntity<?> getByUserId(@PathVariable("id") Long id) {
        UserDto userDto = userApiService.getById(id);
        if (userDto.getId() != null) {
            return new ResponseEntity<>(waterApiService.findByUserId(id), HttpStatus.OK);
        }
        return new ResponseEntity<>("User with id: " + id + " not found", HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<?> createWater(@Valid @RequestBody WaterDto waterDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining("\n")));
        }
        waterApiService.saveWater(waterDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(waterDto);
    }
}
