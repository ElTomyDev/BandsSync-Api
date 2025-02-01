package com.heavydelay.BandsSync.Api.model.dto.user_preference;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserPreferenceRequestDto {

    @NotBlank(message = "Preference key cannot be blank")
    @Size(min = 2, max = 100, message = "Preference key cannot exceed 100 characters and cannot be less than 2 characters")
    private String preferenceKey;

    @NotBlank(message = "Preference value cannot be blank")
    @Size(min = 2, max = 100, message = "Preference value cannot exceed 255 characters and cannot be less than 2 characters")
    private String preferenceValue;

}
