package com.devtobz.hotelmanagementsystem.service;

import com.devtobz.hotelmanagementsystem.entity.LoginDetails;
import com.devtobz.hotelmanagementsystem.entity.response.AuthResponse;

public interface LoginService {
    AuthResponse authenticate(LoginDetails loginDetails, String name);
    AuthResponse signIn(LoginDetails loginDetails);
}
