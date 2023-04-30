package com.techacademy.validation;

import com.techacademy.repository.AuthenticationRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;

@Component
public class EmployeeCodeValidator implements Validator {
    private AuthenticationRepository authenticationRepository;

    public EmployeeCodeValidator(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }
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
            if (existingAuth != null ) {
                errors.rejectValue("authentication.code", "code.alreadyExists", "社員番号が既に存在します。");
            }
        }
    }
}