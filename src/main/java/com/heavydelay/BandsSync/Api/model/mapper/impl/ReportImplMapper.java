package com.heavydelay.BandsSync.Api.model.mapper.impl;

import com.heavydelay.BandsSync.Api.model.dto.external_data.report.ReportResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Report;
import com.heavydelay.BandsSync.Api.model.mapper.IReportMapper;

public class ReportImplMapper implements IReportMapper{

    @Override
    public ReportResponseDto toBasicDto(Report report) {
        return ReportResponseDto.builder()
               .idReport(report.getIdReport())
               .usernameReporter(report.getUserReporter().getUsername())
               .usernameReported(report.getUserReported().getUsername())
               .reportType(report.getReportType())
               .reportReason(report.getReportReason())
               .isResolved(report.getIsResolved())
               .resolutionType(report.getResolutionType())
               .build();
    }

    @Override
    public ReportResponseDto toDetailedDto(Report report) {
        return ReportResponseDto.builder()
               .idReport(report.getIdReport())
               .userReporter(report.getUserReporter())
               .userReported(report.getUserReported())
               .reportType(report.getReportType())
               .reportReason(report.getReportReason())
               .reportDate(report.getReportDate())
               .isResolved(report.getIsResolved())
               .resolutionDate(report.getResolutionDate())
               .resolutionType(report.getResolutionType())
               .build();
    }

}
