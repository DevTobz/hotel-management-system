package com.devtobz.hotelmanagementsystem.service;

import com.devtobz.hotelmanagementsystem.entity.dto.CustomerDto;
import com.devtobz.hotelmanagementsystem.entity.request.CustomerRequest;

public interface CustomerService {
     CustomerDto addCustomer(CustomerRequest customerRequest);
     CustomerDto updateCheckInStatus(String name,int roomNumber);
     Object updateCheckOutStatus(String name, int roomNumber);
}
