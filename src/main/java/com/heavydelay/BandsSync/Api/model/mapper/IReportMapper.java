package com.heavydelay.BandsSync.Api.model.mapper;

import com.heavydelay.BandsSync.Api.model.dto.external_data.report.ReportResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Report;

public interface IReportMapper {
    ReportResponseDto toBasicDto(Report report);
    ReportResponseDto toDetailedDto(Report report);
}
