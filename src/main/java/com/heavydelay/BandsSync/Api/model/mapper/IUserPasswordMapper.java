package com.heavydelay.BandsSync.Api.model.mapper;

import com.heavydelay.BandsSync.Api.model.dto.user_password.UserPasswordResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.UserPassword;

public interface IUserPasswordMapper {
    UserPasswordResponseDto toBasicDto(UserPassword userPassword);
    UserPasswordResponseDto toDetailedDto(UserPassword userPassword);
    UserPasswordResponseDto toPasswordUnHashedDto(UserPassword userPassword);
}
