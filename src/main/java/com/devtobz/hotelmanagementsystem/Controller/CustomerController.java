package com.devtobz.hotelmanagementsystem.Controller;

import com.devtobz.hotelmanagementsystem.Entity.Request.CustomerCheckUpdate;
import com.devtobz.hotelmanagementsystem.Entity.Request.CustomerRequest;
import com.devtobz.hotelmanagementsystem.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/homepage")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "customer/addCustomer")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerRequest customerRequest){
       return ResponseEntity.ok(customerService.addCustomer(customerRequest));
    }

    @PutMapping(path = "customer/updateCheckInStatus")
    public ResponseEntity<String> updateCheckInStatus(@RequestParam String name,
                                                      @RequestParam int roomNumber){
       return ResponseEntity.ok(customerService.updateCheckInStatus(name,roomNumber));
    }

    @PutMapping(path="customer/updateCheckOutStatus")
    public ResponseEntity<String> updateCheckOutStatus(@RequestParam String name,
                                                       @RequestParam int roomNumber){
        return ResponseEntity.ok(customerService.updateCheckOutStatus(name,roomNumber));
    }

}
