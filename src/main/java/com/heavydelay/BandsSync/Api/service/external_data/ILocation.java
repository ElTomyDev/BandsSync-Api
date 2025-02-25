package com.heavydelay.BandsSync.Api.service.external_data;

import com.heavydelay.BandsSync.Api.model.dto.external_data.location.LocationRequestDto;
import com.heavydelay.BandsSync.Api.model.entity.User;

public interface ILocation {
    void createLocation(User user);
    void updateLocation(User user, LocationRequestDto dto);
}
