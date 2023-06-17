package com.devtobz.hotelmanagementsystem.repository;

import com.devtobz.hotelmanagementsystem.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
    Optional<Room> findRoomByRoomNumber(int roomNumber);
}
