package com.devtobz.hotelmanagementsystem.service.serviceImpl;

import com.devtobz.hotelmanagementsystem.config.EmployeeUserDetails;
import com.devtobz.hotelmanagementsystem.entity.Employee;
import com.devtobz.hotelmanagementsystem.entity.Enum.Role;
import com.devtobz.hotelmanagementsystem.entity.LoginDetails;
import com.devtobz.hotelmanagementsystem.exception.EmployeeException;
import com.devtobz.hotelmanagementsystem.repository.EmployeeRepository;
import com.devtobz.hotelmanagementsystem.service.LoginService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @Transactional
    public String authenticate(LoginDetails loginDetails, String email) {
        Employee employee = employeeRepository.findByEmail(email).
                orElseThrow(()->new EmployeeException("Employee not found in the database"));

        if(employee.getRole().equals(Role.Receptionist)||employee.getRole().equals(Role.Manager)){
            employee.setUsername(loginDetails.getUsername());
            employee.setPassword(passwordEncoder.encode(loginDetails.getPassword()));
            employeeRepository.save(employee);
            return "User credentials created successfully";
        }else{
            return "You do not have the roles required for such actions";
        }
    }

    public String signIn(LoginDetails loginDetails) {
        Employee employee = employeeRepository.findByUsername(loginDetails.getUsername()).
                orElseThrow(()-> new EmployeeException("Employee wasn't found in the database"));

        EmployeeUserDetails employeeUserDetails = new EmployeeUserDetails(employee);
        String token = jwtService.generateToken(employeeUserDetails);

        return token;


    }
}
