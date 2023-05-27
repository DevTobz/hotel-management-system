package com.devtobz.hotelmanagementsystem.Service;

import com.devtobz.hotelmanagementsystem.Entity.Enum.BedType;
import com.devtobz.hotelmanagementsystem.Entity.Room;
import com.devtobz.hotelmanagementsystem.Repository.RoomRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;
    @InjectMocks
    private RoomService roomService;

    @Test
    void createRoom() {
    }

    @Test
    void getAllRoom() {
        //Given
        List<Room> roomList = new ArrayList<>();
        Room roomTest1 = Room.builder().
                roomNumber(60).
                price(9000).
                availabilityStatus("Available").
                cleanStatus("Cleaned").
                bedType(BedType.Full_Double).
                build();

        Room roomTest2= Room.builder().
                roomNumber(79).
                price(19000).
                availabilityStatus("Available").
                cleanStatus("Cleaned").
                bedType(BedType.King).
                build();

        roomList.add(roomTest1);
        roomList.add(roomTest2);
        //Mock
        when(roomRepository.findAll()).thenReturn(roomList);
        //When
        List<Room> foundRoomList = roomService.getAllRoom();
        //Assert
        Assertions.assertThat(foundRoomList).containsAll(roomList);
        Assertions.assertThat(roomList).size().isGreaterThanOrEqualTo(2);

    }

    @Test
    void updateRoomByRoomNumber() {
    }

    @Test
    void getAvailableRooms() {
        //Given
      List<Room> availableRoomList= new ArrayList<>();
        Room roomTest1 = Room.builder().
                roomNumber(60).
                price(9000).
                availabilityStatus("Available").
                cleanStatus("Cleaned").
                bedType(BedType.Full_Double).
                build();
        Room roomTest2= Room.builder().
                roomNumber(79).
                price(19000).
                availabilityStatus("Occupied").
                cleanStatus("Cleaned").
                bedType(BedType.King).
                build();

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
        Room roomTest1 = Room.builder().
                roomNumber(60).
                price(9000).
                availabilityStatus("Available").
                cleanStatus("Cleaned").
                bedType(BedType.Full_Double).
                build();
        Room roomTest2= Room.builder().
                roomNumber(79).
                price(19000).
                availabilityStatus("Occupied").
                cleanStatus("Cleaned").
                bedType(BedType.King).
                build();
        Room roomTest3= Room.builder().
                roomNumber(80).
                price(14000).
                availabilityStatus("Available").
                cleanStatus("Not Cleaned").
                bedType(BedType.King).
                build();
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