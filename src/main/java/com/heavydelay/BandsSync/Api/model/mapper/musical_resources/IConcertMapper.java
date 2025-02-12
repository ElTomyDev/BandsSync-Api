package com.heavydelay.BandsSync.Api.model.mapper.musical_resources;

import com.heavydelay.BandsSync.Api.model.dto.musical_resources.concert.ConcertResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Concert;

public interface IConcertMapper {
    ConcertResponseDto toBasicDto(Concert concert);
    ConcertResponseDto toDetailedDto(Concert concert);
}
