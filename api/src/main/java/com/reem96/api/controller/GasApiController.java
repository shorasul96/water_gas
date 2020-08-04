package com.reem96.api.controller;

// Created by Shorasul Sh. on 29.07.2020

import com.reem96.api.service.GasApiService;
import com.reem96.api.service.UserApiService;
import com.reem96.domain.dto.GasDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @PostMapping(value = "/", headers = {"Accept=application/json", "Content-type=application/json"})
    public ResponseEntity<String> createGas(@Validated @RequestBody GasDto gasDto, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining("\n")));
        }

        if (userApiService.checkUser(gasDto.getUserId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id not found");
        }
        gasApiService.saveGas(gasDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Success!");
    }

}
