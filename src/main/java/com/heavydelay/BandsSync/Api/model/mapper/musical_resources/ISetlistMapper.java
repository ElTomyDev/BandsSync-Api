package com.heavydelay.BandsSync.Api.model.mapper.musical_resources;

import com.heavydelay.BandsSync.Api.model.dto.musical_resources.setlist.SetlistResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Setlist;

public interface ISetlistMapper {
    SetlistResponseDto toBasicDto(Setlist setlist);
    SetlistResponseDto toDetailedDto(Setlist setlist);
}
