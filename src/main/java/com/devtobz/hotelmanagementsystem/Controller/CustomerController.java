package com.devtobz.hotelmanagementsystem.Controller;

import com.devtobz.hotelmanagementsystem.Entity.Dto.CustomerDto;
import com.devtobz.hotelmanagementsystem.Entity.Request.CustomerCheckUpdate;
import com.devtobz.hotelmanagementsystem.Entity.Request.CustomerRequest;
import com.devtobz.hotelmanagementsystem.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/homepage")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/customer/addCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerRequest customerRequest){
       return new ResponseEntity<>(customerService.addCustomer(customerRequest), HttpStatus.CREATED);
    }

    @PutMapping(path = "/customer/updateCheckInStatus")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateCheckInStatus(@RequestParam String name,
                                                      @RequestParam int roomNumber){
       return new ResponseEntity<>(customerService.updateCheckInStatus(name,roomNumber),HttpStatus.OK);
    }

    @PutMapping(path="/customer/updateCheckOutStatus")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateCheckOutStatus(@RequestParam String name,
                                                       @RequestParam int roomNumber){
        return new ResponseEntity<>(customerService.updateCheckOutStatus(name,roomNumber),HttpStatus.OK);
    }

}
