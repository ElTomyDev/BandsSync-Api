package com.heavydelay.BandsSync.Api.model.entity;

import java.util.Date;

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
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_album")
    private Long idAlbum;

    @ManyToOne
    @JoinColumn(name = "id_band", nullable = false)
    private Band band;

    @ManyToOne
    @JoinColumn(name = "id_setlist", nullable = false)
    private Setlist setlist;

    @Column(name = "album_name", nullable = false)
    private String albumName;

    @Column(name = "release_date", nullable = true)
    private Date releaseDate;

    @Column(name = "cover_image", nullable = true)
    private String coverImage;

    @Column(name = "songs_count", nullable = false)
    @Builder.Default
    private Byte songsCount = 0;

    @Column(name = "is_public", nullable = false)
    @Builder.Default
    private Boolean isPublic = false;


}
