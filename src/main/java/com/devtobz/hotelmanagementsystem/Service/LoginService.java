package com.devtobz.hotelmanagementsystem.Service;

import com.devtobz.hotelmanagementsystem.entity.Employee;
import com.devtobz.hotelmanagementsystem.entity.Enum.Role;
import com.devtobz.hotelmanagementsystem.entity.LoginDetails;
import com.devtobz.hotelmanagementsystem.Repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public String authenticate(LoginDetails loginDetails, String name) {
        Employee employee = employeeRepository.findByName(name).
                orElseThrow(()->new RuntimeException("Employee not found in the database"));

        if(employee.getRole().equals(Role.Receptionist)||employee.getRole().equals(Role.Manager)){
            employee.setUsername(loginDetails.getUsername());
            employee.setPassword(loginDetails.getPassword());
            employeeRepository.save(employee);
            return "User credentials created successfully";
        }else{
            return "You do not have the roles required for such actions";
        }
    }

}
