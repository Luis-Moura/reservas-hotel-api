package com.reservashoteis.dao.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.reservashoteis.model.Room;

@Repository
public class RoomDao {
    private final JdbcTemplate template;

    @Autowired
    public RoomDao(JdbcTemplate template) {
        this.template = template;
    }

    public List<Room> searchAll() {
        String sql = "SELECT * FROM quartos";
        return template.query(sql, new BeanPropertyRowMapper<>(Room.class));
    }

}
