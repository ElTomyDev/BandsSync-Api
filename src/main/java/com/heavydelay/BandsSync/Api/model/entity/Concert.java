package com.heavydelay.BandsSync.Api.model.entity;

import java.time.LocalDateTime;

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
@Table(name = "concerts")
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concert")
    private Long idConcert;

    @ManyToOne
    @JoinColumn(name = "id_band", nullable = false, updatable = false)
    private Band band;

    @ManyToOne
    @JoinColumn(name = "id_locate", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "id_setlist", nullable = false)
    private Setlist setlist;

    @Column(name = "concert_name", nullable = false)
    private String concertName;

    @Column(name = "concert_date", nullable = false)
    private LocalDateTime concertDate;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "tickets_available", nullable = true)
    private Integer ticketsAvailable;

}
