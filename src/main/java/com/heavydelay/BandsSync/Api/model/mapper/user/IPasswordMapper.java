package com.heavydelay.BandsSync.Api.model.mapper.user;

import com.heavydelay.BandsSync.Api.model.dto.user.user_password.UserPasswordResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.UserPassword;

public interface IPasswordMapper {
    UserPasswordResponseDto toBasicDto(UserPassword userPassword);
    UserPasswordResponseDto toDetailedDto(UserPassword userPassword);
    UserPasswordResponseDto toPasswordUnHashedDto(UserPassword userPassword);
}
