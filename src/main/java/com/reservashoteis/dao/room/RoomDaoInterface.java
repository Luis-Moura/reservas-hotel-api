package com.reservashoteis.dao.room;

import java.util.List;
import java.util.Optional;

import com.reservashoteis.model.Room;

public interface RoomDaoInterface {
    void save(Room room);

    Optional<Room> findById(Long id);

    Optional<Room> findByNumber(int number);

    List<Room> findAll();

    boolean update(Room room);

    boolean delete(Long id);
}
