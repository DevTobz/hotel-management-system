package com.devtobz.hotelmanagementsystem.Entity;

import com.devtobz.hotelmanagementsystem.Entity.Enum.BedType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Room {

    @Id
    @SequenceGenerator(name = "room_sequence",sequenceName = "room_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    private int roomNumber;
    private String availabilityStatus;
    private String cleanStatus;
    private int price;

    @Enumerated(EnumType.STRING)
    private BedType bedType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "Customer_Id")
    private Customer customer;

}
