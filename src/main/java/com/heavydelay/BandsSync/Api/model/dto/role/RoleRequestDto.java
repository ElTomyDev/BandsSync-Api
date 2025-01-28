package com.heavydelay.BandsSync.Api.model.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RoleRequestDto {

    @NotBlank(message = "Role name cannot be blank")
    @Size(max = 50, message = "Role name cannot exceed 50 characters")
    private String roleName;

}
