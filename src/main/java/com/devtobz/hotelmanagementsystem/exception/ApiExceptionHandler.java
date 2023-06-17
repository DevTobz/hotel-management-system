package com.devtobz.hotelmanagementsystem.exception;

import com.devtobz.hotelmanagementsystem.entity.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {CustomerException.class})
    public ResponseEntity<ApiResponse> customerDoesNotExist(CustomerException customerException){
        ApiResponse apiResponse = new ApiResponse(ZonedDateTime.now(),false,customerException.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RoomException.class})
    public ResponseEntity<ApiResponse> roomDoesNotExist(RoomException roomException){
        ApiResponse apiResponse = new ApiResponse(ZonedDateTime.now(),false,roomException.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EmployeeException.class})
    public ResponseEntity<ApiResponse> employeeDoesNotExist(EmployeeException employeeException){
        ApiResponse apiResponse = new ApiResponse(ZonedDateTime.now(),false,employeeException.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }



}
