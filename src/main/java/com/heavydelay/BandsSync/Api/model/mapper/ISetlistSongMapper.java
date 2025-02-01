package com.heavydelay.BandsSync.Api.model.mapper;

import com.heavydelay.BandsSync.Api.model.dto.setlist.SetlistSongResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.SetlistSong;

public interface ISetlistSongMapper {
    SetlistSongResponseDto toBasicDto(SetlistSong setlistSong);
    SetlistSongResponseDto toDetailedDto(SetlistSong setlistSong);
}
