package com.heavydelay.BandsSync.Api.service.user.impl;

import org.springframework.stereotype.Service;

import com.heavydelay.BandsSync.Api.exception.ResourceNotFoundException;
import com.heavydelay.BandsSync.Api.model.dto.user.location.LocationRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.user.location.LocationResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Location;
import com.heavydelay.BandsSync.Api.model.entity.User;
import com.heavydelay.BandsSync.Api.model.mapper.user.ILocationMapper;
import com.heavydelay.BandsSync.Api.repository.user.LocationRepository;
import com.heavydelay.BandsSync.Api.service.user.ILocation;

@Service
public class LocationImplService implements ILocation{

    LocationRepository locationRepository;
    ILocationMapper locationMapper;


    public LocationImplService(LocationRepository locationRepository, ILocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    @Override
    public Location createEmptyLocation() {
        Location location = Location.builder()
                            .country("")
                            .state("")
                            .city("")
                            .postalCode("")
                            .address("")
                            .build();
        locationRepository.save(location);
        return location;
    }

    @Override
    public LocationResponseDto updateLocationForUser(User user, LocationRequestDto dto) {
        Location location = locationRepository.findById(user.getLocation().getIdLocation()).orElseThrow(
            () -> new ResourceNotFoundException("The location with ID '" + user.getLocation().getIdLocation() + "' was not found")
        );

        if (!dto.getCountry().isEmpty()) {
            location.setCountry(dto.getCountry());
        }
        if (!dto.getState().isEmpty()){
            location.setState(dto.getState());
        }
        if (!dto.getCity().isEmpty()){
            location.setCity(dto.getCity());
        }
        if (!dto.getPostalCode().isEmpty()) {
            location.setPostalCode(dto.getPostalCode());
        }
        if (!dto.getAddress().isEmpty()) {
            location.setAddress(dto.getAddress());
        }

        locationRepository.save(location);

        return locationMapper.toBasicDto(location);
    }
    
    @Override
    public void deleteLocationByUser(User user){
        Location location = locationRepository.findById(user.getLocation().getIdLocation()).orElseThrow(
            () -> new ResourceNotFoundException("The location with ID '" + user.getLocation().getIdLocation() + "' was not found")
        );

        locationRepository.delete(location);
    }
}
