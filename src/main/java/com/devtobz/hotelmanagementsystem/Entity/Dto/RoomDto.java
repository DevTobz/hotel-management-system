package com.devtobz.hotelmanagementsystem.Entity.Dto;

import com.devtobz.hotelmanagementsystem.Entity.Customer;
import com.devtobz.hotelmanagementsystem.Entity.Enum.BedType;
import lombok.Builder;

@Builder
public record RoomDto(int roomNumber,
                      String availabilityStatus,
                      String cleanStatus,
                      int price,
                      BedType bedType,
                      CustomerDto customerDto) {
}
