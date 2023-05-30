package com.devtobz.hotelmanagementsystem.Entity.Request;

import com.devtobz.hotelmanagementsystem.Entity.Enum.Gender;
import com.devtobz.hotelmanagementsystem.Entity.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRequest {
    private String name;
    private int age;
    private Gender gender;
    private Role role;
    private int salary;
    private String phoneNumber;
    private String address;
    private String email;
}
