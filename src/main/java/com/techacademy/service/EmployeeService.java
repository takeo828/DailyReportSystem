package com.techacademy.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository repository) {
        this.employeeRepository = repository;
    }

    public List<Employee> getEmployeeList(){
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Integer id) {
        return employeeRepository.findById(id).get();
    }

    public long countAllEmployees() {
        return employeeRepository.count();
    }

    @Transactional
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

}