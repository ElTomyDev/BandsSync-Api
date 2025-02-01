package com.heavydelay.BandsSync.Api.model.dto.album;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AlbumRequestDto {


    @NotBlank(message = "Setlist name must not be blank")
    @Size(min = 3, max = 100, message = "Setlist name It must not exceed 100 characters and must have a minimum of 3")
    private String setlistName;

    @NotBlank(message = "Setlist name must not be blank")
    @Size(max = 100, message = "Album name it must not exceed 100 characters.")
    private String albumName;

    @NotNull(message = "Release date field must not be empty")
    private Date releaseDate;

    @NotBlank(message = "Cover image must not be blank")
    @Size(max = 255, message = "Cover image it must not exceed 255 characters.")
    private String coverImage;

    @NotNull(message = "Songs count field must not be empty")
    private Byte songsCount;

    @NotNull(message = "Is public field must not be empty")
    private Boolean isPublic;

}
