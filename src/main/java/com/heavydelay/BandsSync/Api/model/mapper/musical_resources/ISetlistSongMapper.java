package com.heavydelay.BandsSync.Api.model.mapper.musical_resources;

import com.heavydelay.BandsSync.Api.model.dto.musical_resources.setlist.SetlistSongResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.SetlistSong;

public interface ISetlistSongMapper {
    SetlistSongResponseDto toBasicDto(SetlistSong setlistSong);
    SetlistSongResponseDto toDetailedDto(SetlistSong setlistSong);
}
