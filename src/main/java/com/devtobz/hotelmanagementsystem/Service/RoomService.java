package com.devtobz.hotelmanagementsystem.Service;

import com.devtobz.hotelmanagementsystem.entity.dto.RoomDto;
import com.devtobz.hotelmanagementsystem.entity.mapper.RoomMapper;
import com.devtobz.hotelmanagementsystem.entity.request.RoomUpdate;
import com.devtobz.hotelmanagementsystem.entity.Room;
import com.devtobz.hotelmanagementsystem.entity.request.RoomRequest;
import com.devtobz.hotelmanagementsystem.Repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMapper roomMapper;

    //Creates Room and persist to the database
    public RoomDto createRoom(RoomRequest roomRequest) {
        Room room = new Room();
        room.setRoomNumber(roomRequest.getRoomNumber());
        room.setPrice(roomRequest.getPrice());
        room.setBedType(roomRequest.getBedType());
        room.setCleanStatus(roomRequest.getCleanStatus());

        if(roomRequest.isRoomAvailability()){
            room.setAvailabilityStatus("Available");
        }else{
            room.setAvailabilityStatus("Occupied");
        }
        roomRepository.save(room);

        return roomMapper.apply(room);
    }

    //Gets all rooms
    public List<Room> getAllRoom() {

        return roomRepository.findAll();
    }

    @Transactional
    public RoomDto updateRoomStatusByRoomNumber(RoomUpdate roomUpdate, int roomNumber) {
        Room room = roomRepository.findRoomByRoomNumber(roomNumber).
                orElseThrow(()->new RuntimeException("Room wasn't found in the database"));

        if(roomUpdate.isRoomAvailability()){
            room.setAvailabilityStatus("Available");
        }else{
            room.setAvailabilityStatus("Occupied");
        }
        room.setCleanStatus(roomUpdate.getCleanStatus());
        roomRepository.save(room);

        return roomMapper.apply(room);
    }

    //returns every available room
    public List<Room> getAvailableRooms() {
        List<Room> availableRoomList = roomRepository.findAll().
                                        stream().
                                        filter(room -> room.getAvailabilityStatus().equals("Available")).
                                        toList();
        return availableRoomList;
    }

    //Returns all Available cleaned rooms
    public List<Room> getAllCleanedAndAvailableRooms() {
        List<Room> availableCleanedRoomList = roomRepository.findAll().stream().
                filter(room-> room.getAvailabilityStatus().equals("Available") && room.getCleanStatus().equals("Cleaned")).
                toList();
        return availableCleanedRoomList;
    }
}
