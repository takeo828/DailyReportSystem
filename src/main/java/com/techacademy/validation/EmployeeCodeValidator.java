package com.techacademy.validation;

import com.techacademy.repository.AuthenticationRepository;
import com.techacademy.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;

@Component
public class EmployeeCodeValidator implements Validator {
    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;
        String code = employee.getAuthentication().getCode();

        if (code != null && !code.isEmpty()) {
            Authentication existingAuth = authenticationRepository.findByCode(code);
            Employee existingEmployee = null;

            if (existingAuth != null) {
                existingEmployee = employeeRepository.findById(existingAuth.getEmployee().getId()).orElse(null);
            }

            if (existingEmployee != null && !Integer.valueOf(existingEmployee.getId()).equals(Integer.valueOf(employee.getId()))) {
                errors.rejectValue("authentication.code", "code.alreadyExists", "社員番号が既に存在します。");
            }
        }
    }
}