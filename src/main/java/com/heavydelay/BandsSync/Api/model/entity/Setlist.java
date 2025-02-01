package com.heavydelay.BandsSync.Api.model.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "setlists")
public class Setlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_setlist")
    private Long idSetlist;

    @ManyToOne
    @JoinColumn(name = "id_band", nullable = false, updatable = false)
    private Band band;
    
    @Column(name = "setlist_name", nullable = false)
    private String setlistName;
    
    @CreationTimestamp
    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDateTime createDate;
    
    @Column(name = "in_use", nullable = false)
    @Builder.Default
    private Boolean inUse = true;
    
}
