package com.heavydelay.BandsSync.Api.model.dto.gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class GenderRequestDto {

    @NotBlank(message = "Gender name cannot be blank")
    @Size(max = 50, message = "Gender name cannot exceed 50 characters")
    private String genderName;

}
