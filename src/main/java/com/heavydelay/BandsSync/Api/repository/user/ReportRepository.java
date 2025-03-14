package com.heavydelay.BandsSync.Api.repository.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.BandsSync.Api.enums.ReportType;
import com.heavydelay.BandsSync.Api.enums.ResolutionType;
import com.heavydelay.BandsSync.Api.model.entity.Report;
import com.heavydelay.BandsSync.Api.model.entity.User;

public interface ReportRepository extends CrudRepository<Report, Long>{
    Optional<Report> findByUserReporterAndUserReported(User userReporter, User userReported);

    Iterable<Report> findAllByReportType(ReportType reportType);
    Iterable<Report> findAllByResolutionType(ResolutionType resolutiontType);
    Iterable<Report> findAllByIsResolved(Boolean isResolved);
    Iterable<Report> findAllByUserReporter(User userReporter);
    Iterable<Report> findAllByUserReported(User userReported);

}
