package com.heavydelay.BandsSync.Api.model.dto.setlist;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heavydelay.BandsSync.Api.model.entity.Setlist;
import com.heavydelay.BandsSync.Api.model.entity.Song;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SetlistSongResponseDto {
    
    private Long idSetlistSong;

    private Setlist setlist;
    private String setlistName;

    private Song song;
    private String songName;

    private Integer songOrder;
}
