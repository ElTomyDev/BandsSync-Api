package com.heavydelay.BandsSync.Api.model.dto.location;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationResponseDto {

    private Long idLocation;

    private String country;

    private String state;

    private String city;

    private String postalCode;

    private String address;
}
