package com.devtobz.hotelmanagementsystem.entity.request;

import com.devtobz.hotelmanagementsystem.entity.Enum.BedType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomUpdate {
    private boolean roomAvailability;
    private String cleanStatus;
    private int price;
    private BedType bedType;
}
