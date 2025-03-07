package com.heavydelay.BandsSync.Api.service.external_data;

import com.heavydelay.BandsSync.Api.model.dto.user.location.LocationRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.user.location.LocationResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Location;
import com.heavydelay.BandsSync.Api.model.entity.User;

public interface ILocation {
    void deleteLocationByUser(User user);
    Location createEmptyLocation();
    LocationResponseDto updateLocationForUser(User user, LocationRequestDto dto);
}
