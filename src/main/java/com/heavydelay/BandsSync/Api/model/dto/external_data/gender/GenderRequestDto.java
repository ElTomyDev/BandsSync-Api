package com.heavydelay.BandsSync.Api.model.dto.external_data.gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GenderRequestDto {

    @NotBlank(message = "Gender name cannot be blank")
    @Size(max = 60, message = "Gender name cannot exceed 60 characters")
    private String genderName;

}
