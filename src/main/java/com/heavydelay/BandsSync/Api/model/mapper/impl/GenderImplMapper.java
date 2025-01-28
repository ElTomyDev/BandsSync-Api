package com.heavydelay.BandsSync.Api.model.mapper.impl;

import com.heavydelay.BandsSync.Api.model.dto.gender.GenderResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Gender;
import com.heavydelay.BandsSync.Api.model.mapper.IGenderMapper;

public class GenderImplMapper implements IGenderMapper{

    @Override
    public GenderResponseDto toBasicDto(Gender gender) {
        return GenderResponseDto.builder()
               .idGender(gender.getIdGender())
               .genderName(gender.getGenderName())
               .build();
    }

}
