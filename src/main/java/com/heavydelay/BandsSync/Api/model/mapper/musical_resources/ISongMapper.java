package com.heavydelay.BandsSync.Api.model.mapper;

import com.heavydelay.BandsSync.Api.model.dto.musical_resources.song.SongResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Song;

public interface ISongMapper {
    SongResponseDto toBasicDto(Song song);
    SongResponseDto toDetailedDto(Song song);
}
