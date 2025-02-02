package com.heavydelay.BandsSync.Api.model.mapper.impl.musical_resources;

import com.heavydelay.BandsSync.Api.model.dto.musical_resources.audio.AudioResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Audio;
import com.heavydelay.BandsSync.Api.model.mapper.IAudioMapper;

public class AuidoImplMapper implements IAudioMapper{

    @Override
    public AudioResponseDto toBasicDto(Audio audio) {
        return AudioResponseDto.builder()
               .idAudio(audio.getIdAudio())
               .format(audio.getFormat())
               .filePath(audio.getFilePath())
               .size(audio.getSize())
               .build();
    }

    @Override
    public AudioResponseDto toDetailedDto(Audio audio) {
        return AudioResponseDto.builder()
               .idAudio(audio.getIdAudio())
               .format(audio.getFormat())
               .filePath(audio.getFilePath())
               .size(audio.getSize())
               .bitrate(audio.getBitrate())
               .uploadDate(audio.getUploadDate())
               .build();
    }
    
}
