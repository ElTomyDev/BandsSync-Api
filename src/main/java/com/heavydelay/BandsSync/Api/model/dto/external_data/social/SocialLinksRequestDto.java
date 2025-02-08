package com.heavydelay.BandsSync.Api.model.dto.external_data.social;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.Size;


// FALTA LAS VALIDACIONES PARA LOS LINKS, osea 'https' o 'http'.
public class SocialLinksRequestDto {
    public interface InstagramView {}
    public interface FacebookView {}
    public interface TwitterView {}
    public interface TikTokView {}
    public interface RedditView {}
    public interface YoutubeView {}
    public interface SpotifyView {}
    public interface SoundCloudView {}
    public interface BandCampView {}
    public interface SocialLinksView extends InstagramView, FacebookView, TwitterView, TikTokView, RedditView, YoutubeView, SpotifyView, SoundCloudView, BandCampView {}

    @JsonView(InstagramView.class)
    @Size(groups = {InstagramView.class}, max = 255, message = "Instagram link cannot exceed 255 characters")
    private String instagram;
    
    @JsonView(FacebookView.class)
    @Size(groups = {FacebookView.class}, max = 255, message = "Facebook link cannot exceed 255 characters")
    private String facebook;
    
    @JsonView(TwitterView.class)
    @Size(groups = {TwitterView.class}, max = 255, message = "Twitter link cannot exceed 255 characters")
    private String twitter;
    
    @JsonView(TikTokView.class)
    @Size(groups = {TikTokView.class}, max = 255, message = "Tiktok link cannot exceed 255 characters")
    private String tiktok;
    
    @JsonView(RedditView.class)
    @Size(groups = {RedditView.class}, max = 255, message = "Reddit link cannot exceed 255 characters")
    private String reddit;
    
    @JsonView(YoutubeView.class)
    @Size(groups = {YoutubeView.class}, max = 255, message = "Youtube link cannot exceed 255 characters")
    private String youtube;
    
    @JsonView(SpotifyView.class)
    @Size(groups = {SpotifyView.class}, max = 255, message = "Spotify link cannot exceed 255 characters")
    private String spotify;
    
    @JsonView(SoundCloudView.class)
    @Size(groups = {SoundCloudView.class}, max = 255, message = "Soundcloud link cannot exceed 255 characters")
    private String soundcloud;
    
    @JsonView(BandCampView.class)
    @Size(groups = {BandCampView.class}, max = 255, message = "Bandcamp link cannot exceed 255 characters")
    private String bandcamp;

}
