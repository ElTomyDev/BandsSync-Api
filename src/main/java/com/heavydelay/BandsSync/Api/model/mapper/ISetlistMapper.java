package com.heavydelay.BandsSync.Api.model.mapper;

import com.heavydelay.BandsSync.Api.model.dto.setlist.SetlistResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Setlist;

public interface ISetlistMapper {
    SetlistResponseDto toBasicDto(Setlist setlist);
    SetlistResponseDto toDetailedDto(Setlist setlist);
}
