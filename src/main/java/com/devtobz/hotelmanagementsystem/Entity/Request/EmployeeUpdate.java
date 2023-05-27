package com.devtobz.hotelmanagementsystem.Entity.Request;

import com.devtobz.hotelmanagementsystem.Entity.Enum.Gender;
import com.devtobz.hotelmanagementsystem.Entity.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

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
