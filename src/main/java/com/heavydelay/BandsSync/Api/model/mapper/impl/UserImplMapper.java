package com.heavydelay.BandsSync.Api.model.mapper.impl;

import com.heavydelay.BandsSync.Api.model.dto.user.UserResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.User;
import com.heavydelay.BandsSync.Api.model.mapper.IUserMapper;

public class UserImplMapper implements IUserMapper{

    @Override
    public UserResponseDto toBasicDto(User user) {
        return UserResponseDto.builder()
               .idUser(user.getIdUser())
               .name(user.getName())
               .lastname(user.getLastname())
               .username(user.getUsername())
               .description(user.getDescription())
               .status(user.getStatus())
               .findBands(user.getFindBands())
               .lastConnection(user.getLastConnection())
               .build();
    }

    @Override
    public UserResponseDto toDetailedDto(User user) {
        return UserResponseDto.builder()
               .idUser(user.getIdUser())
               .name(user.getName())
               .lastname(user.getLastname())
               .username(user.getUsername())
               .description(user.getDescription())
               .status(user.getStatus())
               .phoneNumber(user.getPhoneNumber())
               .findBands(user.getFindBands())
               .lastFailedLogin(user.getLastFailedLogin())
               .failedLoginAttempts(user.getFailedLoginAttempts())
               .lastConnection(user.getLastConnection())
               .createDate(user.getCreateDate())
               .build();
    }

}
