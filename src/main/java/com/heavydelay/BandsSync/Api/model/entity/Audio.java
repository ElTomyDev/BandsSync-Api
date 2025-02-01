package com.heavydelay.BandsSync.Api.model.entity;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "audios")
public class Audio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_audio")
    private Long idAudio;

    @Column(name = "format", nullable = false)
    private String format;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "size", nullable = false)
    private Long size;

    @Column(name = "bitrate", nullable = false)
    @Builder.Default
    private Short bitrate = 192;

    @CreationTimestamp
    @Column(name = "upload_date", nullable = false, updatable = false)
    private Long uploadDate;
}
