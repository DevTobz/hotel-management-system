package com.devtobz.hotelmanagementsystem.Entity.Mapper;

import com.devtobz.hotelmanagementsystem.Entity.Customer;
import com.devtobz.hotelmanagementsystem.Entity.Dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerMapper implements Function<Customer, CustomerDto> {
    @Override
    public CustomerDto apply(Customer customer) {
        return new CustomerDto(customer.getIdentificationType(),
                customer.getPhoneNumber()
                , customer.getName(),
                customer.getGender(),
                customer.getCountry(),
                customer.getCheckInTime(),
                customer.getDeposit(),
                customer.getCheckOutTime(),
                customer.getCheckOutStatus() ,
                customer.getRoom());
    }
}
