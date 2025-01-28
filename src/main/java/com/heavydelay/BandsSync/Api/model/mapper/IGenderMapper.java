package com.heavydelay.BandsSync.Api.model.mapper;

import com.heavydelay.BandsSync.Api.model.dto.gender.GenderResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Gender;

public interface IGenderMapper {
    GenderResponseDto toBasicDto(Gender gender);
}
