package com.reem96.api.controller;

// Created by Shorasul Sh. on 29.07.2020

import com.reem96.api.service.UserApiService;
import com.reem96.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserApiController {

    private final UserApiService userApiService;

    @GetMapping("/")
    List<UserDto> getAll(){
        return userApiService.getAll();
    }

    @GetMapping("/{id}")
    UserDto getUserById(@PathVariable("id") Long id){
        return userApiService.getById(id);
    }

    @PostMapping("/create")
    public String createUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ERROR: " + bindingResult.getAllErrors().get(0).getDefaultMessage();
        } else {
            userApiService.saveUser(userDto);
        }
        return "successfully created";
    }
}