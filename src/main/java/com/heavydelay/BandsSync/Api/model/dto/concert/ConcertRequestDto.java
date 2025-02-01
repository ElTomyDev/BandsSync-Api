package com.heavydelay.BandsSync.Api.model.dto.concert;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ConcertRequestDto {

    @NotNull(message = "Id location field must not be empty")
    private Long idLocation;

    @NotBlank(message = "Setlist name must not be blank")
    @Size(min = 3, max = 100, message = "Setlist name It must not exceed 100 characters and must have a minimum of 3")
    private String setlistName;

    @NotBlank(message = "Concert name must not be blank")
    @Size(max = 100, message = "Concert name It must not exceed 100 characters")
    private String concertName;

    @NotNull(message = "Concert date field must not be empty")
    private LocalDateTime concertDate;

    @NotBlank(message = "Description must not be blank")
    @Size(max = 500, message = "Description It must not exceed 100 characters")
    private String description;

    @NotNull(message = "Tickets available field must not be empty")
    private Integer ticketsAvailable;

}
