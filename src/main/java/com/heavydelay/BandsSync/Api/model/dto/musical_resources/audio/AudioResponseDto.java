package com.heavydelay.BandsSync.Api.model.dto.musical_resources.audio;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AudioResponseDto {
    
    private Long idAudio;

    private String format;

    private String filePath;

    private Long size;

    private Short bitrate;

    private Long uploadDate;
}
