package com.devtobz.hotelmanagementsystem.entity.dto;

import com.devtobz.hotelmanagementsystem.entity.Enum.Gender;
import com.devtobz.hotelmanagementsystem.entity.Enum.Role;
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
