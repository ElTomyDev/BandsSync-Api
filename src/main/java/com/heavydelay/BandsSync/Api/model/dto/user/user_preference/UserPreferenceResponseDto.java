package com.heavydelay.BandsSync.Api.model.dto.user.user_preference;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heavydelay.BandsSync.Api.model.entity.User;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPreferenceResponseDto {

    private Long idPreference;

    private User user;
    private String username;

    private String preferenceKey;

    private String preferenceValue;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
}
