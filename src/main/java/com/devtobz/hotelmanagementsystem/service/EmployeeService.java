package com.devtobz.hotelmanagementsystem.service;

import com.devtobz.hotelmanagementsystem.entity.Employee;
import com.devtobz.hotelmanagementsystem.entity.dto.EmployeeDto;
import com.devtobz.hotelmanagementsystem.entity.request.EmployeeRequest;
import com.devtobz.hotelmanagementsystem.entity.request.EmployeeUpdate;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeRequest request);
    List<Employee> getAllEmployee();
    String editEmployee(String name, EmployeeUpdate employeeUpdate);
    String deleteEmployee(String name);
}
