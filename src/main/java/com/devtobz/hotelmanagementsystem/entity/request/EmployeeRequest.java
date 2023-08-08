package com.devtobz.hotelmanagementsystem.entity.request;

import com.devtobz.hotelmanagementsystem.entity.Enum.Gender;
import com.devtobz.hotelmanagementsystem.entity.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRequest {
    private String firstName;
    private int age;
    private Gender gender;
    private Role role;
    private int salary;
    private String phoneNumber;
    private String lastName;
    private String email;
}
