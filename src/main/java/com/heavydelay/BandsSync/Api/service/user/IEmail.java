package com.heavydelay.BandsSync.Api.service.user;

import com.heavydelay.BandsSync.Api.model.dto.user.UserResponseDto;
import com.heavydelay.BandsSync.Api.model.dto.user.user_email.UserEmailRequestDto;

public interface IEmail {
    UserEmail updateEmail(UserEmailRequestDto dto, String username, Long id);
}
