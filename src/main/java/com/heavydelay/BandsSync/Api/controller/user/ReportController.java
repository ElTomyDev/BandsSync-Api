package com.heavydelay.BandsSync.Api.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            .message("Users successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(reports)
            .build(), HttpStatus.OK
        );
    }
}
