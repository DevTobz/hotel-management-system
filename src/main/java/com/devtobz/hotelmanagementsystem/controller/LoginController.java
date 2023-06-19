package com.devtobz.hotelmanagementsystem.controller;

import com.devtobz.hotelmanagementsystem.entity.LoginDetails;
import com.devtobz.hotelmanagementsystem.entity.response.ApiResponse;
import com.devtobz.hotelmanagementsystem.service.serviceImpl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping(path = "/login")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PutMapping(path = "/authenticate")
    public ResponseEntity<?> Authenticate(@RequestBody LoginDetails loginDetails,
                                       @RequestParam String name){
        ApiResponse apiResponse = ApiResponse.builder().
                isSuccessful(true).
                status(HttpStatus.OK.value()).
                timeStamp(ZonedDateTime.now()).
                data(loginService.authenticate(loginDetails,name)).
                build();
     return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

}
