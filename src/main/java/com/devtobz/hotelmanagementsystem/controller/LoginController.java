package com.devtobz.hotelmanagementsystem.controller;

import com.devtobz.hotelmanagementsystem.entity.LoginDetails;
import com.devtobz.hotelmanagementsystem.entity.response.ApiResponse;
import com.devtobz.hotelmanagementsystem.entity.response.AuthResponse;
import com.devtobz.hotelmanagementsystem.service.LoginService;
import com.devtobz.hotelmanagementsystem.service.serviceImpl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/home")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PutMapping(path = "/signup")
    public ResponseEntity<?> Authenticate(@RequestBody LoginDetails loginDetails,
                                       @RequestParam String email){
        AuthResponse authResponse = loginService.authenticate(loginDetails,email);

        ApiResponse apiResponse = ApiResponse.builder().
                isSuccessful(true).
                status(HttpStatus.OK.value()).
                timeStamp(ZonedDateTime.now()).
                message(authResponse.getMessage()).
                build();
     return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @PostMapping(path = "/signIn")
    public ResponseEntity<?> signIn(@RequestBody LoginDetails loginDetails){
        AuthResponse authResponse = loginService.signIn(loginDetails);
        if(authResponse.getData() == null){
            ApiResponse apiResponse = ApiResponse.builder().
                    isSuccessful(true).
                    status(HttpStatus.BAD_REQUEST.value()).
                    message(authResponse.getMessage())
                    .data(null)
                    .timeStamp(ZonedDateTime.now()).build();
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);

        }else{
            ApiResponse apiResponse = ApiResponse.builder().
                    isSuccessful(true).
                    status(HttpStatus.OK.value()).
                    data(authResponse.getData())
                    .timeStamp(ZonedDateTime.now()).build();
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        }


    }

}
