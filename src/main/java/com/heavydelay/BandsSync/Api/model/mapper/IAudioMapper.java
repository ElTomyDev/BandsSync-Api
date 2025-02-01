package com.heavydelay.BandsSync.Api.model.mapper;

import com.heavydelay.BandsSync.Api.model.dto.audio.AudioResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Audio;

public interface IAudioMapper {
    AudioResponseDto toBasicDto(Audio audio);
    AudioResponseDto toDetailedDto(Audio audio);
}
