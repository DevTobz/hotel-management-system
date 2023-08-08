package com.devtobz.hotelmanagementsystem.entity.mapper;

import com.devtobz.hotelmanagementsystem.entity.dto.EmployeeDto;
import com.devtobz.hotelmanagementsystem.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EmployeeMapper implements Function<Employee, EmployeeDto> {
    @Override
    public EmployeeDto apply(Employee employee) {
        return new EmployeeDto(employee.getFirstName(),
                employee.getAge(),
                employee.getGender(),
                employee.getRole(),
                employee.getSalary(),
                employee.getPhoneNumber(),
                employee.getLastName(),
                employee.getEmail());
    }
}
