package com.heavydelay.BandsSync.Api.model.mapper.external_data.impl;

import org.springframework.stereotype.Component;

import com.heavydelay.BandsSync.Api.model.dto.external_data.gender.GenderResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Gender;
import com.heavydelay.BandsSync.Api.model.mapper.external_data.IGenderMapper;

@Component
public class GenderImplMapper implements IGenderMapper{

    @Override
    public GenderResponseDto toBasicDto(Gender gender) {
        return GenderResponseDto.builder()
               .idGender(gender.getIdGender())
               .genderName(gender.getGenderName())
               .build();
    }

}
