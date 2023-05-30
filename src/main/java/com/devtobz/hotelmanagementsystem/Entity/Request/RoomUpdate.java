package com.devtobz.hotelmanagementsystem.Entity.Request;

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
}
