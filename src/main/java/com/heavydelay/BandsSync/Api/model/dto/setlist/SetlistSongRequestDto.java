package com.heavydelay.BandsSync.Api.model.dto.setlist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SetlistSongRequestDto {

    @NotBlank(message = "Setlist name must not be blank")
    @Size(min = 3, max = 100, message = "Setlist name It must not exceed 100 characters and must have a minimum of 3")
    private String setlistName;

    @NotBlank(message = "Song name must not be blank")
    @Size(max = 100, message = "Song name it must not exceed 100 characters.")
    private String songName;

    @NotNull(message = "Song order field must not be empty")
    private Integer songOrder;
}
