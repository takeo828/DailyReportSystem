package com.techacademy.controller;

import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/register")
    public String getRegister(@ModelAttribute Employee employee) {
        return "employees/register";
    }

    @PostMapping("/register")
    public String postRegister(Employee employee) {
        service.saveEmployee(employee);
        return "redirect:/employees/list";
    }
    }
