package com.devtobz.hotelmanagementsystem.entity;

import com.devtobz.hotelmanagementsystem.entity.Enum.Gender;
import com.devtobz.hotelmanagementsystem.entity.Enum.Role;
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

    private String firstName;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Role role;
    private int salary;
    private String phoneNumber;
    private String lastName;
    private String email;

    private String username;

    private String password;
}
