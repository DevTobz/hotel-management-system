package com.devtobz.hotelmanagementsystem.entity.dto;

import com.devtobz.hotelmanagementsystem.entity.Enum.CheckOutStatus;
import com.devtobz.hotelmanagementsystem.entity.Enum.Gender;
import com.devtobz.hotelmanagementsystem.entity.Enum.IdentificationType;
import com.devtobz.hotelmanagementsystem.entity.Room;
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
