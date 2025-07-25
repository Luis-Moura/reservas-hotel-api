package com.reservashoteis;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.reservashoteis.dao.room.RoomDao;
import com.reservashoteis.model.Room;

@SpringBootApplication
public class ReservasHoteisApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(ReservasHoteisApplication.class, args);

        // Teste do DAO
        RoomDao roomDao = context.getBean(RoomDao.class);
        List<Room> rooms = roomDao.searchAll();
        for (Room room : rooms) {
            // Com o nome dos atributos, get e set diferentes, ele não retorna os dados corretamente.
            System.out.println("Room number: " + room.getNumber() + "\nRoom type: " + room.getRoomType());
        }
        System.exit(0);
    }

}
