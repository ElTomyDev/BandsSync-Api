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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "bands")
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_band")
    private Long idBand;

    @ManyToOne
    @JoinColumn(name = "id_gender", nullable = true)
    private Gender gender;
    
    @ManyToOne
    @JoinColumn(name = "id_social", nullable = true)
    private SocialLinks socialLinks;

    @Column(name = "image_url", nullable = true)
    private String imageURL;

    @Column(name = "band_name", nullable = false, unique = true)
    private String bandName;

    @Column(name = "access_code", nullable = false, unique = true, updatable = false)
    private String accessCode;

    @Column(name = "is_solist")
    @Builder.Default
    private Boolean isSolist = false;

    @Column(name = "find_members")
    @Builder.Default
    private Boolean findMembers = false;

    @Column(name = "biography", nullable = true)
    private String biography;

    @CreationTimestamp
    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDateTime createDate;



}
