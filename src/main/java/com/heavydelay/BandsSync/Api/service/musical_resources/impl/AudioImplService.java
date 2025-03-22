package com.heavydelay.BandsSync.Api.service.musical_resources.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.heavydelay.BandsSync.Api.exception.ResourceNotFoundException;
import com.heavydelay.BandsSync.Api.model.dto.musical_resources.audio.AudioRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.musical_resources.audio.AudioResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Audio;
import com.heavydelay.BandsSync.Api.model.mapper.musical_resources.IAudioMapper;
import com.heavydelay.BandsSync.Api.repository.musical_resources.AudioRepository;
import com.heavydelay.BandsSync.Api.service.musical_resources.IAudio;

public class AudioImplService implements IAudio{

    private AudioRepository audioRepository;
    private IAudioMapper audioMapper;

    public AudioImplService(AudioRepository audioRepository, IAudioMapper audioMapper) {
        this.audioRepository = audioRepository;
        this.audioMapper = audioMapper;
    }

    @Override
    public AudioResponseDto createNewAudio(AudioRequestDto dto) {
        Audio newAudio = Audio.builder()
                         .format(dto.getFormat())
                         .filePath(dto.getFilePath())
                         .size(dto.getSize())
                         .bitrate(dto.getBitrate())
                         //.uploadDate(LocalDateTime.now())
                         .build();
        audioRepository.save(newAudio);
        return audioMapper.toBasicDto(newAudio);
    }

    @Override
    public void deteleAudio(Long idAudio) {
        Audio audioDelete = this.findAudio(idAudio);
        audioRepository.delete(audioDelete);        
    }

    @Override
    public String getFilePath(Long idAudio) {
        Audio audio = this.findAudio(idAudio);

        return audioMapper.toFilePathDto(audio);
    }

    @Override
    public List<AudioResponseDto> showAllAudios(boolean detailed) {
        List<Audio> audios = (List<Audio>) audioRepository.findAll();

        Function<Audio, AudioResponseDto> mapper = detailed ? audioMapper::toDetailedDto : audioMapper::toBasicDto;

        return audios.stream().map(mapper).collect(Collectors.toList());
    }

    @Override
    public AudioResponseDto showAudio(Long idAudio, boolean detailed) {
        Audio audio = this.findAudio(idAudio);

        return detailed ? audioMapper.toDetailedDto(audio) : audioMapper.toBasicDto(audio);
    }

    @Override
    public AudioResponseDto updateBitrate(Long idAudio, AudioRequestDto dto) {
        Audio updateAudio = this.findAudio(idAudio);

        if (dto.getBitrate() != null){
            updateAudio.setBitrate(dto.getBitrate());
        }
        audioRepository.save(updateAudio);
        return audioMapper.toBasicDto(updateAudio);
    }

    @Override
    public AudioResponseDto updateFilePath(Long idAudio, AudioRequestDto dto) {
        Audio updateAudio = this.findAudio(idAudio);

        if (dto.getFilePath() != null){
            updateAudio.setFilePath(dto.getFilePath());
        }
        audioRepository.save(updateAudio);
        return audioMapper.toBasicDto(updateAudio);
    }

    @Override
    public AudioResponseDto updateFormat(Long idAudio, AudioRequestDto dto) {
        Audio updateAudio = this.findAudio(idAudio);

        if (dto.getFormat() != null){
            updateAudio.setFormat(dto.getFormat());
        }
        audioRepository.save(updateAudio);
        return audioMapper.toBasicDto(updateAudio);
    }

    @Override
    public AudioResponseDto updateSize(Long idAudio, AudioRequestDto dto) {
        Audio updateAudio = this.findAudio(idAudio);

        if (dto.getSize() != null){
            updateAudio.setSize(dto.getSize());
        }
        audioRepository.save(updateAudio);
        return audioMapper.toBasicDto(updateAudio);
    }
    
    ///////// AUXILIAR METHODS ///////////////////////////////
    private Audio findAudio(Long idAudio){
        Audio audio;

        if (idAudio != null){
            audio = audioRepository.findById(idAudio).orElseThrow(
                () -> new ResourceNotFoundException("The Audio with ID '" + idAudio + "' not found")
            );
        } else {
            throw new IllegalArgumentException("Paremeter 'idAudio' cannot be null");
        }

        return audio;

    }

}
