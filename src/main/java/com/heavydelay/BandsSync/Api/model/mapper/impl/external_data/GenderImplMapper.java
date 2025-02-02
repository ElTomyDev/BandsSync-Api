package com.heavydelay.BandsSync.Api.model.mapper.impl.external_data;

import com.heavydelay.BandsSync.Api.model.dto.external_data.gender.GenderResponseDto;
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
