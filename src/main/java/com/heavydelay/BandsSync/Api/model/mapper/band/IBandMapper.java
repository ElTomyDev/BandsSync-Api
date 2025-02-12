package com.heavydelay.BandsSync.Api.model.mapper.band;

import com.heavydelay.BandsSync.Api.model.dto.band.BandResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Band;

public interface IBandMapper {
    BandResponseDto toBasicDto(Band band);
    BandResponseDto toDetailedDto(Band band);
    BandResponseDto toAccesCodeDto(Band band);
}
