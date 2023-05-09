package com.techacademy.service;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Report getReport(Integer id) {
        return reportRepository.findById(id).orElse(null);
    }

    public Report saveReport(Report report) {
        report.setCreatedAt(LocalDateTime.now());
        report.setUpdatedAt(LocalDateTime.now());
        return reportRepository.save(report);
    }

    public Report updateReport(Integer id, Report updatedReport) {
        Report report = getReport(id);
        if (report != null) {
            report.setReportDate(updatedReport.getReportDate());
            report.setTitle(updatedReport.getTitle());
            report.setContent(updatedReport.getContent());
            report.setUpdatedAt(LocalDateTime.now());
            return reportRepository.save(report);
        }
        return null;
    }

    public long countAllReports() {
        return reportRepository.count();
    }

    public List<Report> getMyReports(Employee employee) {
        return reportRepository.findByEmployee(employee);
    }

}