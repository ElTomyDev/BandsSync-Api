package com.heavydelay.BandsSync.Api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.heavydelay.BandsSync.Api.model.dto.user.UserRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.user.UserResponseDto;
import com.heavydelay.BandsSync.Api.model.dto.user.UserRequestDto.RegisterView;
import com.heavydelay.BandsSync.Api.service.IUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUser userService;
    
    @PostMapping("/register")
    @JsonView(RegisterView.class)
    public String postMethodName(@RequestBody UserRequestDto dto) {
        UserResponseDto userRegister = userService.registerNewUser(dto);
        return entity;
    }
    
}
