package com.heavydelay.BandsSync.Api.service.external_data.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavydelay.BandsSync.Api.model.dto.external_data.location.LocationRequestDto;
import com.heavydelay.BandsSync.Api.model.entity.Location;
import com.heavydelay.BandsSync.Api.model.entity.User;
import com.heavydelay.BandsSync.Api.repository.external_data.LocationRepository;
import com.heavydelay.BandsSync.Api.service.external_data.ILocation;

@Service
public class LocationImplService implements ILocation{

    @Autowired
    LocationRepository locationRepository;


    @Override
    public Location createEmptyLocationForUser(User user) {
        Location location = Location.builder()
                            .country("")
                            .state("")
                            .city("")
                            .postalCode("")
                            .address("")
                            .build();
        
        user.setLocation(location);
        locationRepository.save(location);
        return location;
    }

    @Override
    public void updateLocation(User user, LocationRequestDto dto) {
        // TODO Auto-generated method stub
        
    }
    
}
