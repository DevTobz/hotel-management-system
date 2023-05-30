package com.devtobz.hotelmanagementsystem.Controller;

import com.devtobz.hotelmanagementsystem.Entity.Dto.EmployeeDto;
import com.devtobz.hotelmanagementsystem.Entity.Employee;
import com.devtobz.hotelmanagementsystem.Entity.Request.EmployeeRequest;
import com.devtobz.hotelmanagementsystem.Entity.Request.EmployeeUpdate;
import com.devtobz.hotelmanagementsystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/homepage")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // Saving Employee to database
    @PostMapping(path = "/employee/createEmployee")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeRequest request){

        return new ResponseEntity<>(employeeService.createEmployee(request),HttpStatus.CREATED);
    }

    // Getting all Employee from the database
    @GetMapping(path = "/employee/getAllEmployee")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Employee>> getAllEmployee (){
        return new ResponseEntity<>(employeeService.getAllEmployee(),HttpStatus.OK);
    }

    //Update Employee by Name
    @PutMapping(path = "/employee/editEmployee")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> editEmployee(EmployeeUpdate employeeUpdate,
                                               @RequestParam String name){

    return new ResponseEntity<>(employeeService.editEmployee(name,employeeUpdate),HttpStatus.OK);

    }

    //Delete employee from the database
    @DeleteMapping(path = "/employee/deleteEmployee")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteEmployee(@RequestParam String name){
        return new  ResponseEntity<>(employeeService.deleteEmployee(name),HttpStatus.OK);

    }




}
