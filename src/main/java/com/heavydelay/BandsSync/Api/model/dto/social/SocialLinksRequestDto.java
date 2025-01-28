package com.heavydelay.BandsSync.Api.model.dto.social;

import jakarta.validation.constraints.Size;


// FALTA LAS VALIDACIONES PARA LOS LINKS, osea 'https' o 'http'.
public class SocialLinksRequestDto {

    @Size(max = 255, message = "Instagram link cannot exceed 255 characters")
    private String instagram;

    @Size(max = 255, message = "Facebook link cannot exceed 255 characters")
    private String facebook;

    @Size(max = 255, message = "Twitter link cannot exceed 255 characters")
    private String twitter;

    @Size(max = 255, message = "Tiktok link cannot exceed 255 characters")
    private String tiktok;

    @Size(max = 255, message = "Reddit link cannot exceed 255 characters")
    private String reddit;

    @Size(max = 255, message = "Youtube link cannot exceed 255 characters")
    private String youtube;

    @Size(max = 255, message = "Spotify link cannot exceed 255 characters")
    private String spotify;

    @Size(max = 255, message = "Soundcloud link cannot exceed 255 characters")
    private String soundcloud;

    @Size(max = 255, message = "Bandcamp link cannot exceed 255 characters")
    private String bandcamp;

}
