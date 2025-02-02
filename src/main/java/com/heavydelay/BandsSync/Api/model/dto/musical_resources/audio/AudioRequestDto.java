package com.heavydelay.BandsSync.Api.model.dto.musical_resources.audio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AudioRequestDto {

    @NotBlank(message = "Format must not be blank")
    @Size(min = 2, max = 10, message = "Format It must not exceed 10 characters and must have a minimum of 2")
    private String format;

    @NotBlank(message = "File path must not be blank")
    @Size(max = 255, message = "File path must have a maximum of 255 characters")
    private String filePath;

    @NotNull(message = "Size must not be empty")
    private Long size;

    @NotNull(message = "Bitrate must not be empty")
    private Short bitrate;

}
