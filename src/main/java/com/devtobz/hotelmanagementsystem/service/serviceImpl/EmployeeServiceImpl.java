package com.devtobz.hotelmanagementsystem.service.serviceImpl;


import com.devtobz.hotelmanagementsystem.entity.dto.EmployeeDto;
import com.devtobz.hotelmanagementsystem.entity.Employee;
import com.devtobz.hotelmanagementsystem.entity.mapper.EmployeeMapper;
import com.devtobz.hotelmanagementsystem.entity.request.EmployeeRequest;
import com.devtobz.hotelmanagementsystem.entity.request.EmployeeUpdate;
import com.devtobz.hotelmanagementsystem.exception.EmployeeException;
import com.devtobz.hotelmanagementsystem.repository.EmployeeRepository;
import com.devtobz.hotelmanagementsystem.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;


    //Create a new employee to the database
    public EmployeeDto createEmployee(EmployeeRequest request) {
       Employee employee= new Employee();

       employee.setLastName(request.getLastName());
       employee.setAge(request.getAge());
       employee.setFirstName(request.getFirstName());
       employee.setGender(request.getGender());
       employee.setEmail(request.getEmail());
       employee.setRole(request.getRole());
       employee.setSalary(request.getSalary());
       employee.setPhoneNumber(request.getPhoneNumber());

       employeeRepository.save(employee);
      EmployeeDto employeeDto = employeeMapper.apply(employee);

       return employeeDto;
    }

    //Get all employee in the database
    public List<Employee> getAllEmployee() {
       /* List<EmployeeDto> employeeList = employeeRepository.
                findAll().
                stream().
                map(employee -> employeeMapper.
                        apply(employee)).
                collect(Collectors.toList());*/
        return employeeRepository.findAll();
    }


    // updates User by name
    @Transactional
    public EmployeeDto editEmployee(String name, EmployeeUpdate employeeUpdate) {
        Employee employee = employeeRepository.findByName(name).
                orElseThrow(()-> new EmployeeException("Employee wasn't found in the database"));

        employee.setEmail(employeeUpdate.getEmail());
        employee.setAge(employeeUpdate.getAge());
        employee.setLastName(employeeUpdate.getLastName());
        employee.setSalary(employeeUpdate.getSalary());
        employee.setPhoneNumber(employeeUpdate.getPhoneNumber());
        employee.setRole(employeeUpdate.getRole());

        employeeRepository.save(employee);

        return employeeMapper.apply(employee);
    }

    //Delete User
    public String deleteEmployee(String name) {

        Employee employee = employeeRepository.findByName(name).
                orElseThrow(()-> new EmployeeException("Employee wasn't found in the database"));
        employeeRepository.delete(employee);
        return "Employee "+ name + " have been deleted from the database";
    }
}
