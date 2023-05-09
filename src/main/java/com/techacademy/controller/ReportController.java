package com.techacademy.controller;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.service.EmployeeDetail;
import com.techacademy.service.EmployeeService;
import com.techacademy.service.ReportService;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("reports")
public class ReportController {

    private final ReportService reportService;
    private final EmployeeService employeeService;

    public ReportController(ReportService reportService, EmployeeService employeeService) {
        this.reportService = reportService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getAllReports(Model model) {
        model.addAttribute("reports", reportService.getAllReports());

        long totalReports = reportService.countAllReports();
        model.addAttribute("totalReports", totalReports); // 日報全件表示用

        return "reports/list";
    }

    @GetMapping("/register")
    public String showRegisterForm(@ModelAttribute Report report, Model model, @AuthenticationPrincipal EmployeeDetail user) {
        int loginEmployeeId = user.getEmployee().getId();
        Employee loginEmployee = employeeService.getEmployee(loginEmployeeId);
        model.addAttribute("employee", loginEmployee);
        return "reports/register";
    }

    @PostMapping("/register")
    public String registerReport(@ModelAttribute Report report, @AuthenticationPrincipal EmployeeDetail user) {
        int loginEmployeeId = user.getEmployee().getId();
        Employee loginEmployee = employeeService.getEmployee(loginEmployeeId);
        report.setEmployee(loginEmployee);

        reportService.saveReport(report);
        return "redirect:/reports";
    }

    @GetMapping("/detail/{id}")
    public String getReportDetail(@PathVariable Integer id, Model model) {
        model.addAttribute("report", reportService.getReport(id));
        return "reports/detail";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Report report = reportService.getReport(id);
        if (report == null) {
            return "redirect:/reports";
        }
        model.addAttribute("report", report);
        return "reports/update";
    }

    @PostMapping("/update/{id}")
    public String updateReport(@PathVariable Integer id, @ModelAttribute Report updatedReport) {
        reportService.updateReport(id, updatedReport);
        return "redirect:/reports";
    }

    @GetMapping("/myreports")
    public String getMyReports(Model model, @AuthenticationPrincipal EmployeeDetail user) {
        Employee loginEmployee = user.getEmployee();
        List<Report> myReports = reportService.getMyReports(loginEmployee);
        model.addAttribute("reports", myReports);
        model.addAttribute("totalReports", myReports.size());
        return "reports/myreports";
    }
}