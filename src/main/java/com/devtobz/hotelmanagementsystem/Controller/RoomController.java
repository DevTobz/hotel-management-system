package com.devtobz.hotelmanagementsystem.Controller;

import com.devtobz.hotelmanagementsystem.Entity.Dto.RoomDto;
import com.devtobz.hotelmanagementsystem.Entity.Request.RoomRequest;
import com.devtobz.hotelmanagementsystem.Entity.Request.RoomUpdate;
import com.devtobz.hotelmanagementsystem.Entity.Room;
import com.devtobz.hotelmanagementsystem.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/homepage")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // create new room
    @PostMapping(path = "/rooms/addRoom")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RoomDto> createRoom(@RequestBody RoomRequest roomRequest){
        return new ResponseEntity<>(roomService.createRoom(roomRequest),HttpStatus.CREATED);
    }
    // view all rooms
    @GetMapping(path = "/rooms/viewAllRooms")
    public ResponseEntity<List<Room>> getAllRoom(){
        return ResponseEntity.ok(roomService.getAllRoom());
    }

    @PutMapping(path = "/rooms/updateRoomStatus")
    public ResponseEntity<String> updateRoomStatus(@RequestBody RoomUpdate roomUpdate,
                                                   @RequestParam int roomNumber){
        return ResponseEntity.ok(roomService.updateRoomByRoomNumber(roomUpdate,roomNumber));
    }

    //Gets all available rooms
    @GetMapping(path = "/rooms/AvailableRooms")
    public ResponseEntity<List<Room>> getAvailableRooms(){
       return ResponseEntity.ok(roomService.getAvailableRooms());
    }

    @GetMapping(path = "/rooms/allCleanedAvailableRoom")
    public ResponseEntity<List<Room>> getAllCleanedAndAvailableRooms(){
        return ResponseEntity.ok(roomService.getAllCleanedAndAvailableRooms());
    }
}
