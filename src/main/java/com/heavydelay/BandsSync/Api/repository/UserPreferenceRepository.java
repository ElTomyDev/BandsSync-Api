package com.heavydelay.BandsSync.Api.repository;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.BandsSync.Api.model.entity.UserPreference;

public interface UserPreferenceRepository extends CrudRepository<UserPreference, Long>{
    
}
