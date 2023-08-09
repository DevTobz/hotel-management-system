package com.devtobz.hotelmanagementsystem.service;

import com.devtobz.hotelmanagementsystem.entity.Room;
import com.devtobz.hotelmanagementsystem.entity.dto.RoomDto;
import com.devtobz.hotelmanagementsystem.entity.request.RoomRequest;
import com.devtobz.hotelmanagementsystem.entity.request.RoomUpdate;

import java.util.List;

public interface RoomService {
    String createRoom(RoomRequest roomRequest);
    List<Room> getAllRoom();
    String updateRoomStatusByRoomNumber(RoomUpdate roomUpdate, int roomNumber);
    List<Room> getAvailableRooms();
    List<Room> getAllCleanedAndAvailableRooms();
}
