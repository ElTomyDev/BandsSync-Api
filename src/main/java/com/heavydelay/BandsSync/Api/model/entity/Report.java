package com.heavydelay.BandsSync.Api.model.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.heavydelay.BandsSync.Api.enums.ReportType;
import com.heavydelay.BandsSync.Api.enums.ResolutionType;

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
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_report")
    private Long idReport;

    @ManyToOne
    @JoinColumn(name = "id_user_reporter", nullable = false)
    private User userReporter;

    @ManyToOne
    @JoinColumn(name = "id_user_reported", nullable = false)
    private User userReported;

    @Column(name = "report_type", nullable = false)
    @Builder.Default
    private ReportType reportType = ReportType.other;

    @Column(name = "report_reason", nullable = false)
    private String reportReason;

    @CreationTimestamp
    @Column(name = "report_date", nullable = false, updatable = false)
    private LocalDateTime reportDate;

    @Column(name = "is_resolved", nullable = false)
    @Builder.Default
    private Boolean isResolved = false;

    @Column(name = "resolution_date", nullable = true)
    private LocalDateTime resolutionDate;

    @Column(name = "resolution_type", nullable = false)
    @Builder.Default
    private ResolutionType resolutionType = ResolutionType.no_action;
}
