package com.devtobz.hotelmanagementsystem.service;

import com.devtobz.hotelmanagementsystem.entity.LoginDetails;

public interface LoginService {
    String authenticate(LoginDetails loginDetails, String name);
    String signIn(LoginDetails loginDetails);
}
