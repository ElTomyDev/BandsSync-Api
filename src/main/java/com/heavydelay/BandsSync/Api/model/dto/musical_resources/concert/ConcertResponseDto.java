package com.heavydelay.BandsSync.Api.model.dto.musical_resources.concert;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heavydelay.BandsSync.Api.model.entity.Band;
import com.heavydelay.BandsSync.Api.model.entity.Location;
import com.heavydelay.BandsSync.Api.model.entity.Setlist;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
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
