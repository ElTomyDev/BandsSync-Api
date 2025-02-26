package com.heavydelay.BandsSync.Api.service.external_data;

import com.heavydelay.BandsSync.Api.model.dto.external_data.location.LocationRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.location.LocationResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Location;
import com.heavydelay.BandsSync.Api.model.entity.User;

public interface ILocation {
    void deleteLocationByUser(User user);
    Location createEmptyLocationForUser(User user);
    LocationResponseDto updateLocationForUser(User user, LocationRequestDto dto);
}
