package com.heavydelay.BandsSync.Api.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@Entity
@Table(name = "social_links")
public class SocialLinks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_social")
    private Long idSocial;

    @Column(name = "instagram", nullable = true)
    @Builder.Default
    private String instagram = null;

    @Column(name = "facebook", nullable = true)
    @Builder.Default
    private String facebook = null;

    @Column(name = "twitter", nullable = true)
    @Builder.Default
    private String twitter = null;

    @Column(name = "tiktok", nullable = true)
    @Builder.Default
    private String tiktok = null;

    @Column(name = "reddit", nullable = true)
    @Builder.Default
    private String reddit = null;

    @Column(name = "youtube", nullable = true)
    @Builder.Default
    private String youtube = null;

    @Column(name = "spotify", nullable = true)
    @Builder.Default
    private String spotify = null;

    @Column(name = "soundcloud", nullable = true)
    @Builder.Default
    private String soundcloud = null;

    @Column(name = "bandcamp", nullable = true)
    @Builder.Default
    private String bandcamp = null;
}
