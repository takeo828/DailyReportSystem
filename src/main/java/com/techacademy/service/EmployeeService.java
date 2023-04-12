package com.techacademy.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository repository) {
        this.employeeRepository = repository;
    }

    public List<Employee> getEmployeeList(){
        return employeeRepository.findAll();
    }
    public long countAllEmployees() {
        return employeeRepository.count();
    }

}
