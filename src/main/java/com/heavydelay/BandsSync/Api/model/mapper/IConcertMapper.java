package com.heavydelay.BandsSync.Api.model.mapper;

import com.heavydelay.BandsSync.Api.model.dto.concert.ConcertResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Concert;

public interface IConcertMapper {
    ConcertResponseDto toBasicDto(Concert concert);
    ConcertResponseDto toDetailedDto(Concert concert);
}
