package com.devtobz.hotelmanagementsystem.Entity.Request;

import com.devtobz.hotelmanagementsystem.Entity.Enum.BedType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomRequest {
    private int roomNumber;
    private String cleanStatus;
    private int price;
    private BedType bedType;
    private boolean roomAvailability;
}
