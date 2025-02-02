package com.heavydelay.BandsSync.Api.model.mapper.impl.user;

import com.heavydelay.BandsSync.Api.model.dto.user.user_preference.UserPreferenceResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.UserPreference;
import com.heavydelay.BandsSync.Api.model.mapper.IUserPreferenceMapper;

public class UserPreferenceImplMapper implements IUserPreferenceMapper{

    @Override
    public UserPreferenceResponseDto toBasicDto(UserPreference userPreference) {
        return UserPreferenceResponseDto.builder()
               .idPreference(userPreference.getIdPreference())
               .username(userPreference.getUser().getUsername())
               .preferenceKey(userPreference.getPreferenceKey())
               .preferenceValue(userPreference.getPreferenceValue())
               .updateDate(userPreference.getUpdateDate())
               .build();
    }

    @Override
    public UserPreferenceResponseDto toDetailedDto(UserPreference userPreference) {
        return UserPreferenceResponseDto.builder()
               .idPreference(userPreference.getIdPreference())
               .user(userPreference.getUser())
               .preferenceKey(userPreference.getPreferenceKey())
               .preferenceValue(userPreference.getPreferenceValue())
               .createDate(userPreference.getCreateDate())
               .updateDate(userPreference.getUpdateDate())
               .build();
    }

}
