package com.techacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.techacademy.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{
    List<Employee> findByDeleteFlagFalse();

}
