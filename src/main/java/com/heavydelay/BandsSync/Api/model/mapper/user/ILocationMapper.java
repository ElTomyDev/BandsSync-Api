package com.heavydelay.BandsSync.Api.model.mapper.user;

import com.heavydelay.BandsSync.Api.model.dto.user.location.LocationResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Location;

public interface ILocationMapper {
    LocationResponseDto toBasicDto(Location location);
    LocationResponseDto toDetailedDto(Location location);
}
