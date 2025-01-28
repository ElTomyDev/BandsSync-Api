package com.heavydelay.BandsSync.Api.model.mapper.impl;

import com.heavydelay.BandsSync.Api.model.dto.location.LocationResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Location;
import com.heavydelay.BandsSync.Api.model.mapper.ILocationMapper;

public class LocationImplMapper implements ILocationMapper{

    @Override
    public LocationResponseDto toBasicDto(Location location) {
        return LocationResponseDto.builder()
               .idLocation(location.getIdLocation())
               .country(location.getCountry())
               .state(location.getState())
               .city(location.getCity())
               .build();
    }

    @Override
    public LocationResponseDto toDetailedDto(Location location) {
        return LocationResponseDto.builder()
               .idLocation(location.getIdLocation())
               .country(location.getCountry())
               .state(location.getState())
               .city(location.getCity())
               .postalCode(location.getPostalCode())
               .address(location.getAddress())
               .build();
    }

}
