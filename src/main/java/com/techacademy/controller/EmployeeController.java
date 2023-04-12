package com.techacademy.controller;

import com.techacademy.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

@GetMapping
public String getList(Model model) {
    model.addAttribute("employeelist", service.getEmployeeList());

    long totalEmployees = service.countAllEmployees();
    model.addAttribute("totalEmployees", totalEmployees);

    return "employees/list";
}

    }
