package com.heavydelay.BandsSync.Api.model.mapper;

import com.heavydelay.BandsSync.Api.model.dto.user.user_email.UserEmailResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.UserEmail;

public interface IUserEmailMapper {
    UserEmailResponseDto toBasicDto(UserEmail userEmail);
    UserEmailResponseDto toDetailedDto(UserEmail userEmail);
}
