package com.devtobz.hotelmanagementsystem.Entity.Mapper;

import com.devtobz.hotelmanagementsystem.Entity.Dto.RoomDto;
import com.devtobz.hotelmanagementsystem.Entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RoomMapper implements Function<Room, RoomDto> {
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public RoomDto apply(Room room) {
        return new RoomDto(room.getRoomNumber(),
                room.getAvailabilityStatus(),
                room.getCleanStatus(),
                room.getPrice(),
                room.getBedType(),
               customerMapper.apply(room.getCustomer())
        );
    }
}
