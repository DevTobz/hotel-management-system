package com.devtobz.hotelmanagementsystem.entity.request;

import com.devtobz.hotelmanagementsystem.entity.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeUpdate {
    private int age;
    private Role role;
    private int salary;
    private String phoneNumber;
    private String address;
    private String email;
}
