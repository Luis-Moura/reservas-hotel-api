package com.reservashoteis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservashoteis.dto.request.RoomRequestDto;
import com.reservashoteis.dto.response.RoomResponseDto;
import com.reservashoteis.model.Room;
import com.reservashoteis.services.room.RoomServiceInterface;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    private RoomServiceInterface roomService;

    @PostMapping("")
    public ResponseEntity<Void> createRoom(@RequestBody RoomRequestDto roomRequestDto) {
        roomService.createRoom(roomRequestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("")
    public ResponseEntity<List<RoomResponseDto>> findAllRooms() {
        List<Room> rooms = roomService.findAllRooms();

        List<RoomResponseDto> response = rooms.stream().map(room -> new RoomResponseDto(
            room.getIdRoom(),
            room.getNumber(),
            room.getRoomType(),
            room.getDailyPrice()
        )).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}") public ResponseEntity<RoomResponseDto> findRoomById(@PathVariable Long id) {
        Room room = roomService.findRoomById(id);

        RoomResponseDto response = new RoomResponseDto(
                room.getIdRoom(),
                room.getNumber(),
                room.getRoomType(),
                room.getDailyPrice());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRoom(@PathVariable Long id, @RequestBody RoomRequestDto roomRequestDto) {
        roomService.updateRoom(id, roomRequestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
