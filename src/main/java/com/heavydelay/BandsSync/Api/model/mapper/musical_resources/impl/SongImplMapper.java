package com.heavydelay.BandsSync.Api.model.mapper.musical_resources.impl;

import com.heavydelay.BandsSync.Api.model.dto.musical_resources.song.SongResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Song;
import com.heavydelay.BandsSync.Api.model.mapper.musical_resources.ISongMapper;

public class SongImplMapper implements ISongMapper{

    @Override
    public SongResponseDto toBasicDto(Song song) {
        return SongResponseDto.builder()
               .idSong(song.getIdSong())
               .songName(song.getSongName())
               .duration(song.getDuration())
               .audio(song.getAudio())
               .bandName(song.getBand().getBandName())
               .albumName(song.getAlbum().getAlbumName())
               .isPublic(song.getIsPublic())
               .build();
    }

    @Override
    public SongResponseDto toDetailedDto(Song song) {
        return SongResponseDto.builder()
               .idSong(song.getIdSong())
               .songName(song.getSongName())
               .duration(song.getDuration())
               .audio(song.getAudio())
               .band(song.getBand())
               .album(song.getAlbum())
               .isDraft(song.getIsDraft())
               .lyrics(song.getLyrics())
               .bpm(song.getBpm())
               .isPublic(song.getIsPublic())
               .build();
    }

}
