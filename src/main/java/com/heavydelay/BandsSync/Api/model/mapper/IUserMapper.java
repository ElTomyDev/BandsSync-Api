package com.heavydelay.BandsSync.Api.model.mapper;

import org.springframework.context.annotation.Bean;

import com.heavydelay.BandsSync.Api.model.dto.user.UserResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.User;

@Bean
public interface IUserMapper {
    UserResponseDto toBasicDto(User user);
    UserResponseDto toDetailedDto(User user);
}
