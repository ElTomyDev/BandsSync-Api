package com.heavydelay.BandsSync.Api.model.mapper.impl;

import com.heavydelay.BandsSync.Api.model.dto.musical_resources.concert.ConcertResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Concert;
import com.heavydelay.BandsSync.Api.model.mapper.IConcertMapper;

public class ConcertImplMapper implements IConcertMapper{

    @Override
    public ConcertResponseDto toBasicDto(Concert concert) {
        return ConcertResponseDto.builder()
               .idConcert(concert.getIdConcert())
               .bandName(concert.getBand().getBandName())
               .setlistName(concert.getSetlist().getSetlistName())
               .concertName(concert.getConcertName())
               .concertDate(concert.getConcertDate())
               .description(concert.getDescription())
               .build();
    }

    @Override
    public ConcertResponseDto toDetailedDto(Concert concert) {
        return ConcertResponseDto.builder()
               .idConcert(concert.getIdConcert())
               .band(concert.getBand())
               .location(concert.getLocation())
               .setlist(concert.getSetlist())
               .concertName(concert.getConcertName())
               .concertDate(concert.getConcertDate())
               .description(concert.getDescription())
               .ticketsAvailable(concert.getTicketsAvailable())
               .build();
    }

}
