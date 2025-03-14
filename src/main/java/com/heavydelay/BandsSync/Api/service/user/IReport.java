package com.heavydelay.BandsSync.Api.service.user;

import java.util.List;

import com.heavydelay.BandsSync.Api.enums.ReportType;
import com.heavydelay.BandsSync.Api.enums.ResolutionType;
import com.heavydelay.BandsSync.Api.model.dto.user.report.ReportRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.user.report.ReportResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Report;

public interface IReport {
    //// SHOW METHODS
    List<ReportResponseDto> showAllReports(ReportType reportType, ResolutionType resolutionType, boolean resolved, Long idUserReporter, Long idUserReported, boolean detailed);
    
    ReportResponseDto showReport(Long idReport, Long idUserReporter, Long idUserReported, boolean detailed);

    //// DELETE METHODS
    void deleteReport(Long idReport);

    //// CREATE METHODS
    ReportResponseDto createReport(Long userReporter, Long userReported, ReportRequestDto dto);

    //// UPDATE METHODS
    ReportResponseDto resolveReport(Long idReport, Long idUserReporter, Long idUserReported, ReportRequestDto dto);

    ReportResponseDto updateReportResolutionType(Long idReport, Long idUserReporter, Long idUserReported, ReportRequestDto dto);

    ReportResponseDto updateReportIsResolved(Long idReport, Long idUserReporter, Long idUserReported, ReportRequestDto dto);

    /// AUXILIAR
    Report findReportByIdOrUsers(Long idReport, Long idUserReporter, Long idUserReported);

    Iterable<Report> findAllReportBy(ReportType reportType, ResolutionType resolutionType, Boolean resolved, Long idUserReporter, Long idUserReported);

}
