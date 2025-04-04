package com.heavydelay.BandsSync.Api.model.mapper.external_data;

import com.heavydelay.BandsSync.Api.model.dto.external_data.gender.GenderResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Gender;

public interface IGenderMapper {
    GenderResponseDto toBasicDto(Gender gender);
}
