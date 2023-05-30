package com.devtobz.hotelmanagementsystem.Entity.Dto;

import com.devtobz.hotelmanagementsystem.Entity.Enum.Gender;
import com.devtobz.hotelmanagementsystem.Entity.Enum.Role;
import lombok.Builder;

@Builder
public record EmployeeDto( String name,
                           int age,
                           Gender gender,
                           Role role,
                           int salary,
                           String phoneNumber,
                           String address,
                           String email) {
}
