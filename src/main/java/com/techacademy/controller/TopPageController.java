package com.techacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeDetail;
import com.techacademy.service.ReportService;

@Controller
@RequestMapping()
public class TopPageController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal EmployeeDetail user) {
        Employee loginEmployee = user.getEmployee();
        model.addAttribute("reports", reportService.getMyReports(loginEmployee));
        model.addAttribute("totalReports", reportService.getMyReports(loginEmployee).size());
        return "/reports/myreports";
    }

}