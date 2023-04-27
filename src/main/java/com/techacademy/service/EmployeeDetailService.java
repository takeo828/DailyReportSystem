package com.techacademy.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techacademy.entity.Authentication;
import com.techacademy.repository.AuthenticationRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class EmployeeDetailService implements UserDetailsService {
    private final AuthenticationRepository authenticationRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeDetailService(AuthenticationRepository repository, PasswordEncoder passwordEncoder) {
        this.authenticationRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String employeename) throws UsernameNotFoundException {
        Authentication authentication = authenticationRepository.findByCode(employeename);

        if (authentication == null) {
            throw new UsernameNotFoundException("Exception:Username Not Found");
        }

        // パスワードがエンコードされていなければエンコードする
        if (!passwordEncoder.matches("ktaro", authentication.getPassword())) {
            authentication.setPassword(passwordEncoder.encode(authentication.getPassword()));
        }

        return new EmployeeDetail(authentication.getEmployee());
    }
}