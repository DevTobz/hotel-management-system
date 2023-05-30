package com.devtobz.hotelmanagementsystem.Entity.Mapper;

import com.devtobz.hotelmanagementsystem.Entity.Dto.EmployeeDto;
import com.devtobz.hotelmanagementsystem.Entity.Employee;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EmployeeMapper implements Function<Employee, EmployeeDto> {
    @Override
    public EmployeeDto apply(Employee employee) {
        return new EmployeeDto(employee.getName(),
                employee.getAge(),
                employee.getGender(),
                employee.getRole(),
                employee.getSalary(),
                employee.getPhoneNumber(),
                employee.getAddress(),
                employee.getEmail());
    }
}
