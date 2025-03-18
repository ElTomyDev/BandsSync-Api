package com.heavydelay.BandsSync.Api.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heavydelay.BandsSync.Api.enums.ReportType;
import com.heavydelay.BandsSync.Api.enums.ResolutionType;
import com.heavydelay.BandsSync.Api.model.dto.user.report.ReportResponseDto;
import com.heavydelay.BandsSync.Api.model.payload.MessageResponse;
import com.heavydelay.BandsSync.Api.service.user.IReport;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private IReport reportService;

    ///// GET METHODS ///////////////////////////////////////
    @GetMapping("/reports/{detailed}")
    public ResponseEntity<MessageResponse> showAllReports(@PathVariable boolean detailed) {
        List<ReportResponseDto> reports = reportService.showAllReports(null, null, null, null, null, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Reports successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(reports)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/reports/reportType/{reportType}/{detailed}")
    public ResponseEntity<MessageResponse> showAllReports(@PathVariable ReportType reportType, @PathVariable boolean detailed) {
        List<ReportResponseDto> reports = reportService.showAllReports(reportType, null, null, null, null, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Reports with type '" + reportType + "' successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(reports)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/reports/resolutionType/{resolutionType}/{detailed}")
    public ResponseEntity<MessageResponse> showAllReports(@PathVariable ResolutionType resolutionType, @PathVariable boolean detailed) {
        List<ReportResponseDto> reports = reportService.showAllReports(null, resolutionType, null, null, null, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Reports with resolution type '" + resolutionType + "' successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(reports)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/reports/resolved/{resolved}/{detailed}")
    public ResponseEntity<MessageResponse> showAllReports(@PathVariable Boolean resolved, @PathVariable boolean detailed) {
        List<ReportResponseDto> reports = reportService.showAllReports(null, null, resolved, null, null, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Resolved '" + resolved + "': Reports successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(reports)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/reports/user-reporter/{idUserReporter}/{detailed}")
    public ResponseEntity<MessageResponse> showAllReportsReporter(@PathVariable Long idUserReporter, @PathVariable boolean detailed) {
        List<ReportResponseDto> reports = reportService.showAllReports(null, null, null, idUserReporter, null, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Reports with reporter user ID '" + idUserReporter + "' successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(reports)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/reports/user-reported/{idUserReported}/{detailed}")
    public ResponseEntity<MessageResponse> showAllReportsReported(@PathVariable Long idUserReported, @PathVariable boolean detailed) {
        List<ReportResponseDto> reports = reportService.showAllReports(null, null, null, null, idUserReported, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Reports with reported user ID '" + idUserReported + "' successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(reports)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/show-by-id/{idReport}/{detailed}")
    public ResponseEntity<MessageResponse> showByIdReport(@PathVariable Long idReport, @PathVariable boolean detailed) {
        ReportResponseDto report = reportService.showReport(idReport, null, null, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Report with ID '" + idReport + "' successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(report)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/show-by-user/reporter/{idUserReporter}/reported/{idUserReported}/{detailed}")
    public ResponseEntity<MessageResponse> showByUserReporterAndReported(@PathVariable Long idUserReporter, @PathVariable Long idUserReported,@PathVariable boolean detailed) {
        ReportResponseDto report = reportService.showReport(null, idUserReporter, idUserReported, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Report with user reporter ID '" + idUserReporter + "' and user reported ID '" + idUserReported + "' successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(report)
            .build(), HttpStatus.OK
        );
    }
}
