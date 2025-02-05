package com.heavydelay.BandsSync.Api.model.mapper;

import com.heavydelay.BandsSync.Api.model.dto.user.UserResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.User;


public interface IUserMapper {
    UserResponseDto toBasicDto(User user);
    UserResponseDto toDetailedDto(User user);
}
