package com.heavydelay.BandsSync.Api.model.entity;

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
@Table(name = "setlist_songs")
public class SetlistSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_setlist_song")
    private Long idSetlistSong;

    @ManyToOne
    @JoinColumn(name = "id_setlist")
    private Setlist setlist;

    @ManyToOne
    @JoinColumn(name = "id_song")
    private Song song;

    @Column(name = "song_order", nullable = false)
    private Integer songOrder;
}
