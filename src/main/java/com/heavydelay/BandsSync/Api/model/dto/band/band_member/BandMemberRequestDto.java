package com.heavydelay.BandsSync.Api.model.dto.band.band_member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BandMemberRequestDto {

    @NotBlank(message = "Role name cannot be blank")
    @Size(max = 50, message = "Role name cannot exceed 50 characters")
    private String roleName;

    @NotNull(message = "Is Admin field cannot be null")
    private Boolean isAdmin;

}
