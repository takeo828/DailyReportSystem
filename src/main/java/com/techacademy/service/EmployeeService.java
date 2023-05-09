package com.techacademy.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository repository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = repository;
        this.passwordEncoder = passwordEncoder;
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
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());
        employee.setDeleteFlag(0);
        Authentication authentication = employee.getAuthentication();
        authentication.setPassword(passwordEncoder.encode(authentication.getPassword()));
        authentication.setEmployee(employee);
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee deleteEmployee(Employee employee) {
        employee.setUpdatedAt(LocalDateTime.now());
        employee.setDeleteFlag(1);
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(Integer id, Employee updatedEmployee) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee id: " + id));

        employee.setName(updatedEmployee.getName());
        employee.setUpdatedAt(LocalDateTime.now());

        Authentication updatedAuth = updatedEmployee.getAuthentication();
        Authentication authentication = employee.getAuthentication();

        // パスワードをエンコードしてから更新
        String encodedPassword = passwordEncoder.encode(updatedAuth.getPassword());
        authentication.setPassword(encodedPassword);

        authentication.setRole(updatedAuth.getRole());

        return employeeRepository.save(employee);
    }


}