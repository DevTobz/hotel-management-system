package com.devtobz.hotelmanagementsystem.Entity;

import com.devtobz.hotelmanagementsystem.Entity.Enum.Gender;
import com.devtobz.hotelmanagementsystem.Entity.Enum.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Employee {
    @Id
    @SequenceGenerator(name = "employee_sequence",sequenceName = "employee_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Role role;
    private int salary;
    private String phoneNumber;
    private String address;
    private String email;

    private String username;

    private String password;
}
