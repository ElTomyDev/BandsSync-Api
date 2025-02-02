package com.heavydelay.BandsSync.Api.model.dto.musical_resources.song;

import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heavydelay.BandsSync.Api.model.entity.Album;
import com.heavydelay.BandsSync.Api.model.entity.Audio;
import com.heavydelay.BandsSync.Api.model.entity.Band;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SongResponseDto {

    private Long idSong;

    private String songName;

    private Time duration;

    private Audio audio;

    private Band band;
    private String bandName;

    private Album album;
    private String albumName;

    private Boolean isDraft;

    private String lyrics;

    private Short bpm;

    private Boolean isPublic;

}
