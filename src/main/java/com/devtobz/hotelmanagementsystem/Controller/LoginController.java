package com.devtobz.hotelmanagementsystem.Controller;

import com.devtobz.hotelmanagementsystem.Entity.LoginDetails;
import com.devtobz.hotelmanagementsystem.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PutMapping(path = "/authenticate")
    public String Authenticate(@RequestBody LoginDetails loginDetails,
                             @RequestParam String name){

     return loginService.authenticate(loginDetails,name);
    }

}
