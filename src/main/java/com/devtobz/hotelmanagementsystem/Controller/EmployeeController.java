package com.devtobz.hotelmanagementsystem.Controller;

import com.devtobz.hotelmanagementsystem.Entity.Employee;
import com.devtobz.hotelmanagementsystem.Entity.Request.EmployeeRequest;
import com.devtobz.hotelmanagementsystem.Entity.Request.EmployeeUpdate;
import com.devtobz.hotelmanagementsystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/homepage")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // Saving Employee to database
    @PostMapping(path = "employee/createEmployee")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeRequest request){

        return ResponseEntity.ok(employeeService.createEmployee(request));
    }

    // Getting all Employee from the database
    @GetMapping(path = "employee/getAllEmployee")
    public ResponseEntity<List<Employee>> getAllEmployee (){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    //Update Employee by Name
    @PutMapping(path = "employee/editEmployee")
    public ResponseEntity<String> editEmployee(EmployeeUpdate employeeUpdate,
                                               @RequestParam String name){

    return ResponseEntity.ok(employeeService.editEmployee(name,employeeUpdate));

    }

    //Delete employee from the database
    @DeleteMapping(path = "employee/deleteEmployee")
    public ResponseEntity<String> deleteEmployee(@RequestParam String name){
        return ResponseEntity.ok(employeeService.deleteEmployee(name));

    }




}
