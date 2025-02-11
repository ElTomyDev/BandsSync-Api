package com.heavydelay.BandsSync.Api.service.user;

import com.heavydelay.BandsSync.Api.model.dto.user.user_email.UserEmailRequestDto;
import com.heavydelay.BandsSync.Api.model.entity.UserEmail;

public interface IEmail {
    UserEmail createEmail(UserEmailRequestDto dto, String username, Long id);
    UserEmail updateEmail(UserEmailRequestDto dto, String username, Long id);
}
