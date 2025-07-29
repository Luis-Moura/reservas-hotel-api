package com.reservashoteis.services.room;

import java.util.List;

import com.reservashoteis.dto.request.RoomRequestDto;
import com.reservashoteis.model.Room;

public interface RoomServiceInterface {
    void createRoom(RoomRequestDto roomRequestDto);

    Room findRoomById(Long id);

    List<Room> findAllRooms();

    void updateRoom(Long id, RoomRequestDto roomRequestDto);

    void deleteRoom(Long id);
}
