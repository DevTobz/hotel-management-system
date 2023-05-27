package com.devtobz.hotelmanagementsystem.Entity;

import com.devtobz.hotelmanagementsystem.Entity.Enum.CheckOutStatus;
import com.devtobz.hotelmanagementsystem.Entity.Enum.Gender;
import com.devtobz.hotelmanagementsystem.Entity.Enum.IdentificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Customer {
    @Id
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Customer_Id")
    private Long Id;

    @Enumerated(EnumType.STRING)
    private IdentificationType IdentificationType;
    private String phoneNumber;
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String country;
    private LocalTime checkInTime;
    private int deposit;
    private LocalTime checkOutTime;

    @Enumerated(EnumType.STRING)
    private CheckOutStatus checkOutStatus;
    @OneToMany(mappedBy = "customer")
    private List<Room> room;


}
