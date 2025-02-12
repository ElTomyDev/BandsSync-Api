package com.heavydelay.BandsSync.Api.model.mapper.user;

import com.heavydelay.BandsSync.Api.model.dto.user.user_preference.UserPreferenceResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.UserPreference;

public interface IUserPreferenceMapper {
    UserPreferenceResponseDto toBasicDto(UserPreference userPreference);
    UserPreferenceResponseDto toDetailedDto(UserPreference userPreference);
}
