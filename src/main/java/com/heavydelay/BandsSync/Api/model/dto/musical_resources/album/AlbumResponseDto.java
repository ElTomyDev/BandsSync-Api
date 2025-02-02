package com.heavydelay.BandsSync.Api.model.dto.musical_resources.album;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heavydelay.BandsSync.Api.model.entity.Band;
import com.heavydelay.BandsSync.Api.model.entity.Setlist;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlbumResponseDto {

    private Long idAlbum;

    private Band band;
    private String bandName;

    private Setlist setlist;
    private String setlistName;

    private String albumName;

    private Date releaseDate;

    private String coverImage;

    private Byte songsCount;

    private Boolean isPublic;

}
