package com.heavydelay.BandsSync.Api.model.dto.user.report;

import com.fasterxml.jackson.annotation.JsonView;
import com.heavydelay.BandsSync.Api.enums.ReportType;
import com.heavydelay.BandsSync.Api.enums.ResolutionType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReportRequestDto {

    public interface UpdateReportTypeView {}
    public interface UpdateReportReasonView {}
    public interface UpdateResolutionTypeView {}
    public interface UpdateIsResolvedView {}
    public interface CreateReportView extends UpdateReportTypeView, UpdateReportReasonView {}
    public interface ResolvedReportView extends UpdateIsResolvedView, UpdateResolutionTypeView {}

    @JsonView(UpdateReportTypeView.class)
    @NotNull(groups = {UpdateReportTypeView.class}, message = "Report type cannot be null")
    private ReportType reportType;
    
    @JsonView(UpdateReportReasonView.class)
    @Size(groups = {UpdateReportReasonView.class}, max = 500, message = "Report reason cannot exceed 50 characters")
    private String reportReason;
    
    @JsonView(UpdateIsResolvedView.class)
    @NotNull(groups = {UpdateIsResolvedView.class}, message = "Is resolved cannot be null")
    private Boolean isResolved;
    
    @JsonView(UpdateResolutionTypeView.class)
    @NotNull(groups = {UpdateResolutionTypeView.class}, message = "Resolution type status cannot be null")
    private ResolutionType resolutionType;
}
