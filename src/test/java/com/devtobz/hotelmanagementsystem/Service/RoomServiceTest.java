package com.devtobz.hotelmanagementsystem.Service;

import com.devtobz.hotelmanagementsystem.Entity.Dto.RoomDto;
import com.devtobz.hotelmanagementsystem.Entity.Enum.BedType;
import com.devtobz.hotelmanagementsystem.Entity.Mapper.RoomMapper;
import com.devtobz.hotelmanagementsystem.Entity.Request.RoomRequest;
import com.devtobz.hotelmanagementsystem.Entity.Request.RoomUpdate;
import com.devtobz.hotelmanagementsystem.Entity.Room;
import com.devtobz.hotelmanagementsystem.Repository.RoomRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomMapper roomMapper;
    @InjectMocks
    private RoomService roomService;

    private Room roomTest1;
    private Room roomTest2;
    private Room roomTest3;
    private RoomRequest roomRequest;
    private RoomUpdate roomUpdate;
    private RoomDto roomDto;


    @BeforeEach
    void setUp(){
        roomTest1 = Room.builder().
                roomNumber(60).
                price(9000).
                availabilityStatus("Available").
                cleanStatus("Cleaned").
                bedType(BedType.Full_Double).
                build();


       roomRequest = RoomRequest.
                builder().
                roomNumber(60).
                cleanStatus("Cleaned").
                price(9000).
                bedType(BedType.Full_Double).
                roomAvailability(true).
                build();

        roomTest2= Room.builder().
                roomNumber(79).
                price(19000).
                availabilityStatus("Occupied").
                cleanStatus("Cleaned").
                bedType(BedType.King).
                build();

         roomUpdate = RoomUpdate.
                builder().
                roomAvailability(false).
                cleanStatus("Not_Cleaned").
                build();
        roomTest3= Room.builder().
                roomNumber(80).
                price(14000).
                availabilityStatus("Available").
                cleanStatus("Not Cleaned").
                bedType(BedType.King).
                build();

        roomDto = RoomDto.builder().
                roomNumber(roomTest1.getRoomNumber()).
                price(roomTest1.getPrice()).
                availabilityStatus(roomTest1.getAvailabilityStatus()).
                cleanStatus(roomTest1.getCleanStatus()).
                bedType(roomTest1.getBedType()).build();

    }

    @Test
    void createRoom() {

        //Given

        //Mock
        when(roomRepository.save(roomTest1)).thenReturn(roomTest1);
        when(roomMapper.apply(roomTest1)).thenReturn(roomDto);

        //When
        ArgumentCaptor<Room> roomArgumentCaptor = ArgumentCaptor.forClass(Room.class);
        roomService.createRoom(roomRequest);
        verify(roomRepository).save(roomArgumentCaptor.capture());

        Room roomCaptured = roomArgumentCaptor.getValue();

        //Assert
        Assertions.assertThat(roomCaptured).isEqualTo(roomTest1);
        Assertions.assertThat(roomCaptured.getAvailabilityStatus()).isEqualTo("Available");


    }

    @Test
    void getAllRoom() {
        //Given
        List<Room> roomList = new ArrayList<>();

        roomList.add(roomTest1);
        roomList.add(roomTest2);
        //Mock
        when(roomRepository.findAll()).thenReturn(roomList);
        //When
        List<Room> foundRoomList = roomService.getAllRoom();
        verify(roomRepository).findAll();
        //Assert
        Assertions.assertThat(foundRoomList).containsAll(roomList);
        Assertions.assertThat(roomList).size().isGreaterThanOrEqualTo(2);

    }

    @Test
    void updateRoomByRoomNumber() {
        //Given

        //Mock
        when(roomRepository.findRoomByRoomNumber(60)).thenReturn(Optional.ofNullable(roomTest1));
        when(roomRepository.save(roomTest1)).thenReturn(roomTest1);

        //When

        String methodResult = roomService.updateRoomByRoomNumber(roomUpdate, 60);
        ArgumentCaptor<Room> roomArgumentCaptor = ArgumentCaptor.forClass(Room.class);

        verify(roomRepository).findRoomByRoomNumber(60);
        verify(roomRepository).save(roomArgumentCaptor.capture());

        Room roomCaptured = roomArgumentCaptor.getValue();
        //Assert
        Assertions.assertThat(roomCaptured.getAvailabilityStatus()).isEqualTo("Occupied");
        Assertions.assertThat(roomCaptured.getCleanStatus()).isEqualTo(roomUpdate.getCleanStatus());
        Assertions.assertThat(methodResult).isEqualTo("Room Status have been updated");
        Assertions.assertThat(roomCaptured.getAvailabilityStatus()).isNotEqualTo("Available");
    }

    @Test
    void getAvailableRooms() {
        //Given
      List<Room> availableRoomList= new ArrayList<>();

        availableRoomList.add(roomTest1);
        availableRoomList.add(roomTest2);
        //Mock
        when(roomRepository.findAll()).thenReturn(availableRoomList);
        //When
        List<Room> foundAvailableRoom = roomService.getAvailableRooms();
        //Assert
        Assertions.assertThat(foundAvailableRoom).contains(roomTest1);
        Assertions.assertThat(foundAvailableRoom).size().isEqualTo(1);
    }

    @Test
    void getAllCleanedAndAvailableRooms() {

        //Given
        List<Room> availableRoomList= new ArrayList<>();
        availableRoomList.add(roomTest1);
        availableRoomList.add(roomTest2);
        availableRoomList.add(roomTest3);
        //Mock
        when(roomRepository.findAll()).thenReturn(availableRoomList);

        //When
        List<Room> foundRoomList = roomService.getAllCleanedAndAvailableRooms();

        //Assert
        Assertions.assertThat(foundRoomList).contains(roomTest1);
        Assertions.assertThat(foundRoomList).hasSize(1);
        Assertions.assertThat(foundRoomList).doesNotContain(roomTest3);

    }
}