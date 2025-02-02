package com.heavydelay.BandsSync.Api.model.dto.external_data.report;

import com.heavydelay.BandsSync.Api.enums.ReportType;
import com.heavydelay.BandsSync.Api.enums.ResolutionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReportRequestDto {

    @NotBlank(message = "Username reporter cannot be blank")
    @Size(min = 4, max = 50, message = "Username reporter cannot exceed 50 characters and cannot be less than 4 characters")
    private String usernameReporter;

    @NotBlank(message = "Username reported cannot be blank")
    @Size(min = 4, max = 50, message = "Username reported cannot exceed 50 characters and cannot be less than 4 characters")
    private String usernameReported;

    @NotNull(message = "Report type cannot be null")
    private ReportType reportType;

    @Size(max = 500, message = "Report reason cannot exceed 50 characters")
    private String reportReason;

    @NotNull(message = "Is resolved cannot be null")
    private Boolean isResolved;

    @NotNull(message = "Resolution type status cannot be null")
    private ResolutionType resolutionType;
}
