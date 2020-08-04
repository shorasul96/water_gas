package com.reem96.api.controller;

// Created by Shorasul Sh. on 29.07.2020

import com.reem96.api.service.GasApiService;
import com.reem96.api.service.UserApiService;
import com.reem96.domain.dto.GasDto;
import com.reem96.domain.dto.UserDto;
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
    ResponseEntity<?> getByUserId(@PathVariable("id") Long id) {
        UserDto userDto = userApiService.getById(id);
        if (userDto.getId() != null) {
            return new ResponseEntity<>(gasApiService.findByUserId(id), HttpStatus.OK);
        }
        return new ResponseEntity<>("User with id: " + id + " not found", HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<?> createGas(@Valid @RequestBody GasDto gasDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining("\n")));
        }
        gasApiService.saveGas(gasDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(gasDto);
    }

}
