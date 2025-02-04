package com.heavydelay.BandsSync.Api.model.mapper.impl.user;

import com.heavydelay.BandsSync.Api.model.dto.user.UserResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.User;
import com.heavydelay.BandsSync.Api.model.mapper.IUserMapper;

public class UserImplMapper implements IUserMapper{

    @Override
    public UserResponseDto toBasicDto(User user) {
        return UserResponseDto.builder()
               .idUser(user.getIdUser())
               .roleName(user.getRole().getRoleName())
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
               .role(user.getRole())
               .location(user.getLocation())
               .socialLinks(user.getSocialLinks())
               .imageURL(user.getImageURL())
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
