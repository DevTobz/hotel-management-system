package com.devtobz.hotelmanagementsystem.controller;

import com.devtobz.hotelmanagementsystem.entity.request.EmployeeRequest;
import com.devtobz.hotelmanagementsystem.entity.request.EmployeeUpdate;
import com.devtobz.hotelmanagementsystem.entity.response.ApiResponse;
import com.devtobz.hotelmanagementsystem.service.EmployeeService;
import com.devtobz.hotelmanagementsystem.service.serviceImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping(path = "/homepage")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // Saving Employee to database
    @PostMapping(path = "/employee/createEmployee")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest request){
        ApiResponse apiResponse = ApiResponse.builder().
                isSuccessful(true).
                status(HttpStatus.CREATED.value()).
                timeStamp(ZonedDateTime.now()).
                data(employeeService.createEmployee(request)).
                build();

        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }

    // Getting all Employee from the database
    @GetMapping(path = "/employee/getAllEmployee")
    public ResponseEntity<?> getAllEmployee (){
        ApiResponse apiResponse = ApiResponse.builder().
                isSuccessful(true).
                status(HttpStatus.OK.value()).
                timeStamp(ZonedDateTime.now()).
                data(employeeService.getAllEmployee()).
                build();
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    //Update Employee by Name
    @PutMapping(path = "/employee/editEmployee")
    public ResponseEntity<?> editEmployee(EmployeeUpdate employeeUpdate,
                                               @RequestParam String name){
        ApiResponse apiResponse = ApiResponse.builder().
                isSuccessful(true).
                status(HttpStatus.OK.value()).
                timeStamp(ZonedDateTime.now()).
                data(employeeService.editEmployee(name,employeeUpdate)).
                message("Employee Details updated successfully").
                build();
    return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }

    //Delete employee from the database
    @DeleteMapping(path = "/employee/deleteEmployee")
    public ResponseEntity<?> deleteEmployee(@RequestParam String name){
        String message = employeeService.deleteEmployee(name);
        ApiResponse apiResponse = ApiResponse.builder().
                isSuccessful(true).
                status(HttpStatus.OK.value()).
                timeStamp(ZonedDateTime.now()).
                message(message).
                build();
        return new  ResponseEntity<>(apiResponse,HttpStatus.OK);

    }




}
