package com.heavydelay.BandsSync.Api.model.mapper.impl;

import com.heavydelay.BandsSync.Api.model.dto.user_password.UserPasswordResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.UserPassword;
import com.heavydelay.BandsSync.Api.model.mapper.IUserPasswordMapper;

public class UserPasswordImplMapper implements IUserPasswordMapper{

    @Override
    public UserPasswordResponseDto toBasicDto(UserPassword userPassword) {
        return UserPasswordResponseDto.builder()
               .idPassword(userPassword.getIdPassword())
               .username(userPassword.getUser().getUsername())
               .password(userPassword.getPassword())
               .passwordResetToken(userPassword.getPasswordResetToken())
               .passwordResetExpiry(userPassword.getPasswordResetExpiry())
               .build();
    }

    @Override
    public UserPasswordResponseDto toDetailedDto(UserPassword userPassword) {
        return UserPasswordResponseDto.builder()
               .idPassword(userPassword.getIdPassword())
               .user(userPassword.getUser())
               .password(userPassword.getPassword())
               .passwordResetToken(userPassword.getPasswordResetToken())
               .passwordResetExpiry(userPassword.getPasswordResetExpiry())
               .createDate(userPassword.getCreateDate())
               .build();
    }

    // falta programar que devuela la contraseña sin hashear
    @Override
    public UserPasswordResponseDto toPasswordUnHashedDto(UserPassword userPassword) {
        return null;
    }

}
