package com.heavydelay.BandsSync.Api.model.dto.musical_resources.song;

import java.sql.Time;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SongRequestDto {

    @NotBlank(message = "Song name must not be blank")
    @Size(max = 100, message = "Song name it must not exceed 100 characters.")
    private String songName;

    @NotNull(message = "duration field must not be empty")
    private Time duration;

    @NotNull(message = "Audio id field must not be empty")
    private Long idAudio;

    @NotBlank(message = "Album name must not be blank")
    @Size(max = 100, message = "Album name it must not exceed 100 characters.")
    private String albumName; // esto es para buscar por el nombre del album

    @NotNull(message = "Is draft field must not be empty")
    private Boolean isDraft;

    @NotBlank(message = "lyrics name must not be blank")
    @Size(max = 1000, message = "lyrics it must not exceed 1000 characters.")
    private String lyrics;

    @NotNull(message = "BPM field must not be empty")
    private Short bpm;

    @NotNull(message = "Is public field must not be empty")
    private Boolean isPublic;
}
