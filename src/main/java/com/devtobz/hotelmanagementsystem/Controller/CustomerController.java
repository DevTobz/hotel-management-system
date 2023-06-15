package com.devtobz.hotelmanagementsystem.Controller;

import com.devtobz.hotelmanagementsystem.entity.request.CustomerRequest;
import com.devtobz.hotelmanagementsystem.entity.response.ApiResponse;
import com.devtobz.hotelmanagementsystem.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping(path = "/homepage")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/customer/addCustomer")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerRequest customerRequest){
        ApiResponse apiResponse = ApiResponse.builder().
                isSuccessful(true).
                status(HttpStatus.CREATED.value()).
                timeStamp(ZonedDateTime.now()).
                data(customerService.addCustomer(customerRequest)).
                build();
       return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }

    @PutMapping(path = "/customer/updateCheckInStatus")
    public ResponseEntity<?> updateCheckInStatus(@RequestParam String name,
                                                      @RequestParam int roomNumber){
        customerService.updateCheckInStatus(name,roomNumber);
        ApiResponse apiResponse = ApiResponse.
                builder().
                isSuccessful(true).
                status(HttpStatus.OK.value()).
                timeStamp(ZonedDateTime.now()).
                data(customerService.updateCheckInStatus(name,roomNumber)).
                message("Customer And Room Check In Status Is Updated In The Database").
                build();
       return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PutMapping(path="/customer/updateCheckOutStatus")
    public ResponseEntity<?> updateCheckOutStatus(@RequestParam String name,
                                                       @RequestParam int roomNumber){
        ApiResponse apiResponse = ApiResponse.
                builder().
                isSuccessful(true).
                status(HttpStatus.OK.value()).
                timeStamp(ZonedDateTime.now()).
                data(customerService.updateCheckOutStatus(name,roomNumber))
                .message("Customer And Room CheckOut Status Have Update In the Database")
                .build();
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

}
