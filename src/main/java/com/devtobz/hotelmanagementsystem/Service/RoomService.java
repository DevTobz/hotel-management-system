package com.devtobz.hotelmanagementsystem.Service;

import com.devtobz.hotelmanagementsystem.Entity.Dto.RoomDto;
import com.devtobz.hotelmanagementsystem.Entity.Mapper.RoomMapper;
import com.devtobz.hotelmanagementsystem.Entity.Request.RoomUpdate;
import com.devtobz.hotelmanagementsystem.Entity.Room;
import com.devtobz.hotelmanagementsystem.Entity.Request.RoomRequest;
import com.devtobz.hotelmanagementsystem.Repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public String updateRoomByRoomNumber(RoomUpdate roomUpdate, int roomNumber) {
        Room room = roomRepository.findRoomByRoomNumber(roomNumber).
                orElseThrow(()->new RuntimeException("Room wasn't found in the database"));

        if(roomUpdate.isRoomAvailability()){
            room.setAvailabilityStatus("Available");
        }else{
            room.setAvailabilityStatus("Occupied");
        }
        room.setCleanStatus(roomUpdate.getCleanStatus());
        roomRepository.save(room);

        return"Room Status have been updated";
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
