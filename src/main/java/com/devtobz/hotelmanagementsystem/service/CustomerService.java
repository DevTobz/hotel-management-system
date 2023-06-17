package com.devtobz.hotelmanagementsystem.service;

import com.devtobz.hotelmanagementsystem.entity.Customer;
import com.devtobz.hotelmanagementsystem.entity.dto.CustomerDto;
import com.devtobz.hotelmanagementsystem.entity.Enum.CheckOutStatus;
import com.devtobz.hotelmanagementsystem.entity.mapper.CustomerMapper;
import com.devtobz.hotelmanagementsystem.entity.request.CustomerRequest;
import com.devtobz.hotelmanagementsystem.entity.Room;
import com.devtobz.hotelmanagementsystem.exception.CustomerException;
import com.devtobz.hotelmanagementsystem.exception.RoomException;
import com.devtobz.hotelmanagementsystem.repository.CustomerRepository;
import com.devtobz.hotelmanagementsystem.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private CustomerMapper customerMapper;

    public CustomerDto addCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setIdentificationType(customerRequest.getIdentificationType());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setName(customerRequest.getName());
        customer.setGender(customerRequest.getGender());
        customer.setDeposit(customerRequest.getDeposit());
        customer.setCountry(customerRequest.getCountry());
        customer.setCheckOutStatus(CheckOutStatus.Checked_Out);

        customerRepository.save(customer);

        return customerMapper.apply(customer);
    }

    @Transactional
    public CustomerDto updateCheckInStatus(String name,int roomNumber) {
       Customer customer = customerRepository.findByName(name).
                            orElseThrow(()->new CustomerException("Customer not found in the database"));

       Room room = roomRepository.findRoomByRoomNumber(roomNumber).orElseThrow(()-> new RoomException("Room not found"));


       customer.setCheckInTime(LocalTime.now());

       customer.setCheckOutStatus(CheckOutStatus.Checked_In);


       customerRepository.save(customer);

       room.setAvailabilityStatus("Occupied");

       room.setCustomer(customer);
       roomRepository.save(room);

       return customerMapper.apply(customer);
    }

    @Transactional
    public Object updateCheckOutStatus(String name, int roomNumber) {
       Customer customer = customerRepository.findByName(name).orElseThrow(()-> new CustomerException("Customer not found in the database"));
       Room room = roomRepository.findRoomByRoomNumber(roomNumber).orElseThrow(()-> new RoomException("Room not found"));

       if(room.getPrice()==customer.getDeposit()){
           customer.setCheckOutTime(LocalTime.now());
           customer.setCheckOutStatus(CheckOutStatus.Checked_Out);
           customerRepository.save(customer);

           room.setCustomer(null);
           roomRepository.save(room);

           return customerMapper.apply(customer);
       }else{
           return "Pending balance to be fulfilled";
       }
    }
}
