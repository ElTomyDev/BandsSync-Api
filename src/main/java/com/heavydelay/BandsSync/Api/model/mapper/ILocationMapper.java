package com.heavydelay.BandsSync.Api.model.mapper;

import com.heavydelay.BandsSync.Api.model.dto.external_data.location.LocationResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Location;

public interface ILocationMapper {
    LocationResponseDto toBasicDto(Location location);
    LocationResponseDto toDetailedDto(Location location);
}
