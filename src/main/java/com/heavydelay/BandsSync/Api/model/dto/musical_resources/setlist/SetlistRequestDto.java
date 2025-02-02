package com.heavydelay.BandsSync.Api.model.dto.musical_resources.setlist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SetlistRequestDto {
    
    @NotBlank(message = "Setlist name must not be blank")
    @Size(min = 3, max = 100, message = "Setlist name It must not exceed 100 characters and must have a minimum of 3")
    private String setlistName;

    @NotNull(message = "In use field must not be empty")
    private Boolean inUse;
}
