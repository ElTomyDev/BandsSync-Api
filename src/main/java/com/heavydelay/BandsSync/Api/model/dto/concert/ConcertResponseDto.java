package com.heavydelay.BandsSync.Api.model.dto.concert;

import java.time.LocalDateTime;

import com.heavydelay.BandsSync.Api.model.entity.Band;
import com.heavydelay.BandsSync.Api.model.entity.Location;
import com.heavydelay.BandsSync.Api.model.entity.Setlist;

public class ConcertResponseDto {
    
    private Long idConcert;

    private Band band;
    private String bandName;

    private Location location;

    private Setlist setlist;
    private String setlistName;

    private String concertName;

    private LocalDateTime concertDate;

    private String description;

    private Integer ticketsAvailable;
}
