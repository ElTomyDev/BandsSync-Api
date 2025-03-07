package com.heavydelay.BandsSync.Api.model.dto.user.report;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heavydelay.BandsSync.Api.enums.ReportType;
import com.heavydelay.BandsSync.Api.enums.ResolutionType;
import com.heavydelay.BandsSync.Api.model.entity.User;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportResponseDto {

    private Long idReport;

    private User userReporter;
    private String usernameReporter;

    private User userReported;
    private String usernameReported;

    private ReportType reportType;

    private String reportReason;

    private LocalDateTime reportDate;

    private Boolean isResolved;

    private LocalDateTime resolutionDate;

    private ResolutionType resolutionType;
}
