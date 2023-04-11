package com.techacademy.controller;

import com.techacademy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

@Autowired
private EmployeeService employeeService;

@GetMapping("/list")
public String list(Model model) {
    List<Employee> employees = employeeService.findAllActiveEmployees();
    model.addAttribute("employees", employees);
    model.addAttribute("employeeCount", employees.size());
    return "employee/list";
}



    }
