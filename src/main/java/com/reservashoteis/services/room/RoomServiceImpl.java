package com.reservashoteis.services.room;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import com.reservashoteis.dao.room.RoomDaoInterface;
import com.reservashoteis.dto.request.RoomRequestDto;
import com.reservashoteis.model.Room;

public class RoomServiceImpl implements RoomServiceInterface {
    @Autowired
    private RoomDaoInterface roomDao;
    
    @Override
    public void createRoom(RoomRequestDto roomRequestDto) {
        Room room = new Room(roomRequestDto.number(), roomRequestDto.roomType(), roomRequestDto.dailyPrice());

        Room existingRoom = roomDao.findByNumber(roomRequestDto.number()).orElse(null);

        if (existingRoom != null) {
            throw new IllegalArgumentException("Room with this numbere already exists.");
        }

        roomDao.save(room);
    }
    
    @Override
    public Room findRoomById(Long id) {
        return roomDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + id));
    }

    @Override
    public List<Room> findAllRooms() {
        List<Room> rooms = roomDao.findAll();

        if (rooms.isEmpty()) {
            throw new IllegalArgumentException("No rooms found");
        }

        return rooms;
    }

    @Override
    public void updateRoom(Long id, RoomRequestDto roomRequestDto) {
        Room existingRoom = roomDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + id));
        
        if (existingRoom.getNumber() == roomRequestDto.number()) {
            throw new IllegalArgumentException("Cannot update room with the same number");
        }
        if (Objects.equals(existingRoom.getRoomType(),roomRequestDto.roomType())) {
            throw new IllegalArgumentException("Cannot update room with the same room type");
        }
        if (Objects.equals(existingRoom.getDailyPrice(), roomRequestDto.dailyPrice())) {
            throw new IllegalArgumentException("Cannot update room with the same daily price");
        }

        existingRoom.setNumber(roomRequestDto.number());
        existingRoom.setRoomType(roomRequestDto.roomType());
        existingRoom.setDailyPrice(roomRequestDto.dailyPrice());

        boolean isUpdated = roomDao.update(existingRoom);

        if (!isUpdated) {
            throw new IllegalArgumentException("Failed to update room with id: " + id);
        }
    }
    
    @Override
    public void deleteRoom(Long id) {
        Room existingRoom = roomDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Room not found with id: " + id));
        boolean isDeleted = roomDao.delete(existingRoom.getIdRoom());
        if (!isDeleted) {
            throw new IllegalArgumentException("Failed to delete client with id: " + id);
        }
    }
}
