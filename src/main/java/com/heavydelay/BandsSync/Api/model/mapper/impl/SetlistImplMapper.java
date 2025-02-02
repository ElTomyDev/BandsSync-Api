package com.heavydelay.BandsSync.Api.model.mapper.impl;

import com.heavydelay.BandsSync.Api.model.dto.musical_resources.setlist.SetlistResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Setlist;
import com.heavydelay.BandsSync.Api.model.mapper.ISetlistMapper;

public class SetlistImplMapper implements ISetlistMapper{

    @Override
    public SetlistResponseDto toBasicDto(Setlist setlist) {
        return SetlistResponseDto.builder()
               .idSetlist(setlist.getIdSetlist())
               .bandName(setlist.getBand().getBandName())
               .setlistName(setlist.getSetlistName())
               .build();
    }

    @Override
    public SetlistResponseDto toDetailedDto(Setlist setlist) {
        return SetlistResponseDto.builder()
               .idSetlist(setlist.getIdSetlist())
               .band(setlist.getBand())
               .setlistName(setlist.getSetlistName())
               .createDate(setlist.getCreateDate())
               .inUse(setlist.getInUse())
               .build();
    }

}
