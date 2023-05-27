package com.devtobz.hotelmanagementsystem.Service;

import com.devtobz.hotelmanagementsystem.Entity.Employee;
import com.devtobz.hotelmanagementsystem.Entity.Request.EmployeeRequest;
import com.devtobz.hotelmanagementsystem.Entity.Request.EmployeeUpdate;
import com.devtobz.hotelmanagementsystem.Repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    //Create a new employee to the database
    public String createEmployee(EmployeeRequest request) {
       Employee employee= new Employee();

       employee.setAddress(request.getAddress());
       employee.setAge(request.getAge());
       employee.setName(request.getName());
       employee.setGender(request.getGender());
       employee.setEmail(request.getEmail());
       employee.setRole(request.getRole());
       employee.setSalary(request.getSalary());
       employee.setPhoneNumber(request.getPhoneNumber());

       employeeRepository.save(employee);

       return "Employee Created Successfully";
    }

    //Get all employee in the database
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }


    // updates User by name
    @Transactional
    public String editEmployee(String name, EmployeeUpdate employeeUpdate) {
        Employee employee = employeeRepository.findByName(name).
                orElseThrow(()-> new RuntimeException("Employee wasn't found in the database"));

        employee.setEmail(employeeUpdate.getEmail());
        employee.setAge(employeeUpdate.getAge());
        employee.setAddress(employeeUpdate.getAddress());
        employee.setSalary(employeeUpdate.getSalary());
        employee.setPhoneNumber(employeeUpdate.getPhoneNumber());
        employee.setRole(employeeUpdate.getRole());

        employeeRepository.save(employee);

        return "Employee updated";
    }

    //Delete User
    public String deleteEmployee(String name) {

        Employee employee = employeeRepository.findByName(name).
                orElseThrow(()-> new RuntimeException("Employee wasn't found in the database"));
        employeeRepository.delete(employee);
        return "Employee "+ name + " have been deleted from the database";
    }
}
