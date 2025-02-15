package com.heavydelay.BandsSync.Api.repository.external_data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.BandsSync.Api.model.entity.Gender;

public interface GenderRepository extends CrudRepository<Gender, Integer>{
    Optional<Gender> findByGenderName(String genderName);
}
