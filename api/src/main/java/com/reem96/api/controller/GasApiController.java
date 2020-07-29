package com.reem96.api.controller;

// Created by Shorasul Sh. on 29.07.2020

import com.reem96.api.service.GasApiService;
import com.reem96.api.service.UserApiService;
import com.reem96.domain.dto.GasDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gas")
@RequiredArgsConstructor
public class GasApiController {

    private final GasApiService gasApiService;
    private final UserApiService userApiService;

    @GetMapping("/")
    List<GasDto> getAll() {
        return gasApiService.getAll();
    }

    @GetMapping("/{id}")
    List<GasDto> getByUserId(@PathVariable("id") Long id) {
        return gasApiService.findByUserId(id);
    }

    @ResponseBody
    @PostMapping("/create")
    public ResponseEntity<String> createGas(@RequestBody GasDto gasDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Unsuccess!");

        } else {
            if (userApiService.checkUser(gasDto.getUserId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User id not found");
            }
            gasApiService.saveGas(gasDto);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Success!");
    }

}
