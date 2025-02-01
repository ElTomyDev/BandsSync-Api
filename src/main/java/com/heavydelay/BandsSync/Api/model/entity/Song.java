package com.heavydelay.BandsSync.Api.model.entity;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_song")
    private Long idSong;

    @Column(name = "song_name", nullable = false)
    private String songName;

    @Column(name = "duration", nullable = true)
    private Time duration;

    @ManyToOne
    @JoinColumn(name = "id_audio", nullable = true)
    private Audio audio;

    @ManyToOne
    @JoinColumn(name = "id_band", nullable = false)
    private Band band;

    @ManyToOne
    @JoinColumn(name = "id_album", nullable = true)
    private Album album;

    @Column(name = "is_draft", nullable = false)
    @Builder.Default
    private Boolean isDraft = false;

    @Column(name = "lyrics", nullable = true)
    private String lyrics;

    @Column(name = "bpm", nullable = false)
    @Builder.Default
    private Short bpm = 120;

    @Column(name = "is_public", nullable = false)
    @Builder.Default
    private Boolean isPublic = false;

}
