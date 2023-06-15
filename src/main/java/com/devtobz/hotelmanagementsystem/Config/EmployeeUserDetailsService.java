package com.devtobz.hotelmanagementsystem.Config;

import com.devtobz.hotelmanagementsystem.entity.Employee;
import com.devtobz.hotelmanagementsystem.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username).orElseThrow(()->new RuntimeException("Employee Username not found"));
       EmployeeUserDetails employeeUserDetails = new EmployeeUserDetails(employee);
       return employeeUserDetails;
    }
}
