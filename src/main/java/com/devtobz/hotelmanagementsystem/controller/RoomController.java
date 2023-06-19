package com.devtobz.hotelmanagementsystem.controller;

import com.devtobz.hotelmanagementsystem.entity.request.RoomRequest;
import com.devtobz.hotelmanagementsystem.entity.request.RoomUpdate;
import com.devtobz.hotelmanagementsystem.entity.response.ApiResponse;
import com.devtobz.hotelmanagementsystem.service.serviceImpl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping(path = "/homepage")
public class RoomController {

    @Autowired
    private RoomServiceImpl roomService;

    // create new room
    @PostMapping(path = "/rooms/addRoom")
    public ResponseEntity<?> createRoom(@RequestBody RoomRequest roomRequest){
        ApiResponse apiResponse = ApiResponse.builder().
                isSuccessful(true).
                status(HttpStatus.CREATED.value()).
                timeStamp(ZonedDateTime.now()).
                data(roomService.createRoom(roomRequest)).
                build();
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }
    // view all rooms
    @GetMapping(path = "/rooms/viewAllRooms")
    public ResponseEntity<?> getAllRoom(){
        ApiResponse apiResponse = ApiResponse.builder().
                isSuccessful(true).
                status(HttpStatus.OK.value()).
                timeStamp(ZonedDateTime.now()).
                data(roomService.getAllRoom()).
                build();
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PutMapping(path = "/rooms/updateRoomStatus")
    public ResponseEntity<?> updateRoomStatus(@RequestBody RoomUpdate roomUpdate,
                                                   @RequestParam int roomNumber){
        ApiResponse apiResponse = ApiResponse.builder().
                isSuccessful(true).
                status(HttpStatus.OK.value()).
                timeStamp(ZonedDateTime.now()).
                data(roomService.updateRoomStatusByRoomNumber(roomUpdate,roomNumber)).
                message("Room Status have been updated").
                build();
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    //Gets all available rooms
    @GetMapping(path = "/rooms/AvailableRooms")
    public ResponseEntity<?> getAvailableRooms(){
        ApiResponse apiResponse = ApiResponse.builder().
                isSuccessful(true).
                status(HttpStatus.OK.value()).
                timeStamp(ZonedDateTime.now()).
                data(roomService.getAvailableRooms()).
                build();

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping(path = "/rooms/allCleanedAvailableRoom")
    public ResponseEntity<?> getAllCleanedAndAvailableRooms(){
        ApiResponse apiResponse = ApiResponse.builder().
                isSuccessful(true).
                status(HttpStatus.OK.value()).
                timeStamp(ZonedDateTime.now()).
                data(roomService.getAllCleanedAndAvailableRooms()).
                build();
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
