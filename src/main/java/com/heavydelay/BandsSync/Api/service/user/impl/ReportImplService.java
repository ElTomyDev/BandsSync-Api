package com.heavydelay.BandsSync.Api.service.user.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavydelay.BandsSync.Api.enums.ReportType;
import com.heavydelay.BandsSync.Api.enums.ResolutionType;
import com.heavydelay.BandsSync.Api.exception.ResourceNotFoundException;
import com.heavydelay.BandsSync.Api.model.dto.user.report.ReportRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.user.report.ReportResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Report;
import com.heavydelay.BandsSync.Api.model.entity.User;
import com.heavydelay.BandsSync.Api.repository.user.ReportRepository;
import com.heavydelay.BandsSync.Api.repository.user.UserRepository;
import com.heavydelay.BandsSync.Api.service.user.IReport;

@Service
public class ReportImplService implements IReport{

    ReportRepository reportRepository;
    UserRepository userRepository;

    public ReportImplService(ReportRepository reportRepository, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ReportResponseDto createReport(Long userReporter, Long userReported, ReportRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteReport(Long idReport) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ReportResponseDto resolveReport(Long idReport, Long idUserReporter, Long idUserReported,
            ReportRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ReportResponseDto> showAllReports(ReportType reportType, ResolutionType resolutionType,
            boolean resolved, Long idUserReporter, Long idUserReported, boolean detailed) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ReportResponseDto showReport(Long idReport, Long idUserReporter, Long idUserReported, boolean detailed) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ReportResponseDto updateReportIsResolved(Long idReport, Long idUserReporter, Long idUserReported,
            boolean resolved) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ReportResponseDto updateReportResolutionType(Long idReport, Long idUserReporter, Long idUserReported,
            ReportRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /////////// AUXILIARES //////////////////////////////////////////////
    @Override
    public Iterable<Report> findAllReportBy(ReportType reportType, ResolutionType resolutionType, Boolean resolved, Long idUserReporter, Long idUserReported) {
        Iterable<Report> reports;

        if (reportType != null) {
            reports = reportRepository.findAllByReportType(reportType);
        } else if (resolutionType != null) {
            reports = reportRepository.findAllByResolutionType(resolutionType);
        } else if (resolved != null) {
            reports = reportRepository.findAllByIsResolved(resolved);
        } else if (idUserReporter != null) {
            User userReporter = userRepository.findById(idUserReporter).orElseThrow(
                () -> new ResourceNotFoundException("The User reporter with ID '" + idUserReporter + "' was not found")
            );
            reports = reportRepository.findAllByUserReporter(userReporter);
        } else if (idUserReported != null) {
            User userReported = userRepository.findById(idUserReported).orElseThrow(
                () -> new ResourceNotFoundException("The User reported with ID '" + idUserReported + "' was not found")
            );
            reports = reportRepository.findAllByUserReported(userReported);
        }else {
            reports = reportRepository.findAll();
        }

        return reports;
    }

    @Override
    public Report findReportByIdOrUsers(Long idReport, Long idUserReporter, Long idUserReported) {
        Report report;

        if(idReport != null){
            report = reportRepository.findById(idReport).orElseThrow(
                () -> new ResourceNotFoundException("The report with ID '" + idReport + "' was not found")
            );
        } else if (idUserReporter != null && idUserReported != null){
            User userReporter = userRepository.findById(idUserReporter).orElseThrow(
                () -> new ResourceNotFoundException("The User reporter with ID '" + idUserReporter + "' was not found")
            );
            User userReported = userRepository.findById(idUserReported).orElseThrow(
                () -> new ResourceNotFoundException("The User reported with ID '" + idUserReported + "' was not found")
            );

            report = reportRepository.findByUserReporterAndUserReported(userReporter, userReported).orElseThrow(
                () -> new ResourceNotFoundException("The Report with user reporter ID '" + idUserReporter + "' and user reported ID '"+ idUserReported + "' was not found")
            );
        } else {
            throw new IllegalArgumentException("Paremeters cannot be null");
        }

        return report;
    }
}
