package com.heavydelay.BandsSync.Api.model.mapper.impl;

import com.heavydelay.BandsSync.Api.model.dto.setlist.SetlistSongResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.SetlistSong;
import com.heavydelay.BandsSync.Api.model.mapper.ISetlistSongMapper;

public class SetlistSongImplMapper implements ISetlistSongMapper{

    @Override
    public SetlistSongResponseDto toBasicDto(SetlistSong setlistSong) {
        return SetlistSongResponseDto.builder()
               .idSetlistSong(setlistSong.getIdSetlistSong())
               .setlistName(setlistSong.getSetlist().getSetlistName())
               .songName(setlistSong.getSong().getSongName())
               .songOrder(setlistSong.getSongOrder())
               .build();
    }

    @Override
    public SetlistSongResponseDto toDetailedDto(SetlistSong setlistSong) {
        return SetlistSongResponseDto.builder()
               .idSetlistSong(setlistSong.getIdSetlistSong())
               .setlist(setlistSong.getSetlist())
               .song(setlistSong.getSong())
               .songOrder(setlistSong.getSongOrder())
               .build();
    }

}
