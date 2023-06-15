package com.devtobz.hotelmanagementsystem.entity.dto;

import com.devtobz.hotelmanagementsystem.entity.Enum.BedType;
import lombok.Builder;

@Builder
public record RoomDto(int roomNumber,
                      String availabilityStatus,
                      String cleanStatus,
                      int price,
                      BedType bedType,
                      CustomerDto customerDto) {
}
