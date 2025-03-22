package com.heavydelay.BandsSync.Api.service.musical_resources;

import java.util.List;

import com.heavydelay.BandsSync.Api.model.dto.musical_resources.audio.AudioRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.musical_resources.audio.AudioResponseDto;

public interface IAudio {
    List<AudioResponseDto> showAllAudios(boolean detailed);
    AudioResponseDto showAudio(Long idAudio, boolean detailed);

    String getFilePath(Long idAudio);

    void deteleAudio(Long idAudio);

    AudioResponseDto createNewAudio(AudioRequestDto dto);

    AudioResponseDto updateFormat(Long idAudio, AudioRequestDto dto);
    AudioResponseDto updateFilePath(Long idAudio, AudioRequestDto dto);
    AudioResponseDto updateSize(Long idAudio, AudioRequestDto dto);
    AudioResponseDto updateBitrate(Long idAudio, AudioRequestDto dto);

}
