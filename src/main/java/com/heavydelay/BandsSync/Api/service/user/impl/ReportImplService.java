package com.heavydelay.BandsSync.Api.service.user.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.heavydelay.BandsSync.Api.enums.ReportType;
import com.heavydelay.BandsSync.Api.enums.ResolutionType;
import com.heavydelay.BandsSync.Api.exception.ResourceNotFoundException;
import com.heavydelay.BandsSync.Api.model.dto.user.report.ReportRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.user.report.ReportResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Report;
import com.heavydelay.BandsSync.Api.model.entity.User;
import com.heavydelay.BandsSync.Api.model.mapper.user.IReportMapper;
import com.heavydelay.BandsSync.Api.repository.user.ReportRepository;
import com.heavydelay.BandsSync.Api.repository.user.UserRepository;
import com.heavydelay.BandsSync.Api.service.user.IReport;

@Service
public class ReportImplService implements IReport{

    ReportRepository reportRepository;
    UserRepository userRepository;

    IReportMapper reportMapper;

    public ReportImplService(ReportRepository reportRepository, UserRepository userRepository,
            IReportMapper reportMapper) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.reportMapper = reportMapper;
    }

    @Override
    public ReportResponseDto createReport(Long userReporter, Long userReported, ReportRequestDto dto) {
        User reporterUser = userRepository.findById(userReporter).orElseThrow(
            () -> new ResourceNotFoundException("The user reporter with ID '" + userReporter +"' not found")
        );
        
        User reportedUser = userRepository.findById(userReported).orElseThrow(
            () -> new ResourceNotFoundException("The user reported with ID '" + userReported +"' not found")
        );

        if (userReporter == null || userReported == null){
            throw new IllegalArgumentException("Paremeters cannot be null");
        }

        Report newReport = Report.builder()
                           .userReporter(reporterUser)
                           .userReported(reportedUser)
                           .reportType(dto.getReportType())
                           .reportReason(dto.getReportReason())
                           .reportDate(LocalDateTime.now())
                           .isResolved(false)
                           .resolutionType(ResolutionType.no_action)
                           .build();
        
        reportRepository.save(newReport);

        return reportMapper.toBasicDto(newReport);
    }

    @Override
    public void deleteReport(Long idReport) {
        Report reportDelete = this.findReportByIdOrUsers(idReport, null, null);

        reportRepository.delete(reportDelete);
    }

    @Override
    public ReportResponseDto resolveReport(Long idReport, Long idUserReporter, Long idUserReported,
            ReportRequestDto dto) {
        Report report = this.findReportByIdOrUsers(idReport, idUserReporter, idUserReported);

        report.setResolutionType(dto.getResolutionType());
        report.setResolutionDate(LocalDateTime.now());
        report.setIsResolved(dto.getIsResolved());

        reportRepository.save(report);

        return reportMapper.toBasicDto(report);
    }

    @Override
    public List<ReportResponseDto> showAllReports(ReportType reportType, ResolutionType resolutionType,
            boolean resolved, Long idUserReporter, Long idUserReported, boolean detailed) {
        List<Report> reports = (List<Report>) this.findAllReportBy(reportType, resolutionType, null, idUserReporter, idUserReported);

        Function<Report, ReportResponseDto> mapper = detailed ? reportMapper::toDetailedDto : reportMapper::toBasicDto;

        return reports.stream().map(mapper).collect(Collectors.toList());
    }

    @Override
    public ReportResponseDto showReport(Long idReport, Long idUserReporter, Long idUserReported, boolean detailed) {
        Report report = this.findReportByIdOrUsers(idReport, idUserReporter, idUserReported);
        return detailed ? reportMapper.toDetailedDto(report) : reportMapper.toBasicDto(report);
    }

    @Override
    public ReportResponseDto updateReportIsResolved(Long idReport, Long idUserReporter, Long idUserReported,
            ReportRequestDto dto) {
        
        Report report = this.findReportByIdOrUsers(idReport, idUserReporter, idUserReported);

        report.setIsResolved(dto.getIsResolved());

        reportRepository.save(report);
                
        return reportMapper.toBasicDto(report);
    }

    @Override
    public ReportResponseDto updateReportResolutionType(Long idReport, Long idUserReporter, Long idUserReported,
            ReportRequestDto dto) {
        Report report = this.findReportByIdOrUsers(idReport, idUserReporter, idUserReported);

        report.setResolutionType(dto.getResolutionType());

        reportRepository.save(report);

        return reportMapper.toBasicDto(report);
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
