package com.heavydelay.BandsSync.Api.model.mapper.musical_resources;

import com.heavydelay.BandsSync.Api.model.dto.musical_resources.audio.AudioResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Audio;

public interface IAudioMapper {
    AudioResponseDto toBasicDto(Audio audio);
    AudioResponseDto toDetailedDto(Audio audio);
}
