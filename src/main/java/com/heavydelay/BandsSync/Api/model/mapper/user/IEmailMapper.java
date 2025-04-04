package com.heavydelay.BandsSync.Api.model.mapper.user;

import com.heavydelay.BandsSync.Api.model.dto.user.user_email.UserEmailResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.UserEmail;

public interface IEmailMapper {
    UserEmailResponseDto toBasicDto(UserEmail userEmail);
    UserEmailResponseDto toDetailedDto(UserEmail userEmail);
}
