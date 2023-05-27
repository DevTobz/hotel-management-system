package com.devtobz.hotelmanagementsystem.Entity.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomUpdate {
    private boolean roomAvailability;
    private String cleanStatus;
}
