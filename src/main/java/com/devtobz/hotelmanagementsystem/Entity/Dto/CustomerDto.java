package com.devtobz.hotelmanagementsystem.Entity.Dto;

import com.devtobz.hotelmanagementsystem.Entity.Enum.CheckOutStatus;
import com.devtobz.hotelmanagementsystem.Entity.Enum.Gender;
import com.devtobz.hotelmanagementsystem.Entity.Enum.IdentificationType;
import com.devtobz.hotelmanagementsystem.Entity.Room;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Builder;

import java.time.LocalTime;
import java.util.List;
@Builder
public record CustomerDto( IdentificationType identificationType,
         String phoneNumber,
                           String name,
                           Gender gender,
                           String country,
                           LocalTime checkInTime,
                           int deposit,
                           LocalTime checkOutTime,
                           CheckOutStatus checkOutStatus,
                           List<Room> room) {
}
