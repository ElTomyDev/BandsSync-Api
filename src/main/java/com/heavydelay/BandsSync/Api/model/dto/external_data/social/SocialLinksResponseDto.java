package com.heavydelay.BandsSync.Api.model.dto.external_data.social;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SocialLinksResponseDto {

    private Long idSocial;

    private String instagram;

    private String facebook;

    private String twitter;

    private String tiktok;

    private String reddit;

    private String youtube;

    private String spotify;

    private String soundcloud;

    private String bandcamp;
}
