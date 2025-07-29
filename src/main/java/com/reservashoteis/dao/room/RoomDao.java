package com.reservashoteis.dao.room;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.reservashoteis.model.Room;

@Repository
public class RoomDao implements RoomDaoInterface{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoomDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Room> roomMapper = (resultSet, rowNum) ->
        new Room(
            resultSet.getLong("id"),
            resultSet.getInt("numero"),
            resultSet.getString("tipo"),
            resultSet.getFloat("preco_diaria")        
        );
    
    @Override
    public void save(Room room) {
        String sql = "INSERT INTO quartos(numero, tipo, preco_diaria) VALUES (?,?,?)";

        jdbcTemplate.update(sql, room.getNumber(), room.getRoomType(), room.getDailyPrice());
    }
    
    @Override
    public Optional<Room> findById(Long id) {
        String sql = "SELECT * FROM quartos WHERE id = ?";
        List<Room> results = jdbcTemplate.query(sql, roomMapper, id);
        return results.stream().findFirst();
    }

    @Override
    public Optional<Room> findByNumber(int number) {
        String sql = "SELECT * FROM quartos WHERE number = ?";
        List<Room> results = jdbcTemplate.query(sql, roomMapper, number);
        return results.stream().findFirst();
    }

    @Override
    public List<Room> findAll() {
        String sql = "SELECT * FROM quartos";
        return jdbcTemplate.query(sql, roomMapper);
    }

    @Override
    public boolean update(Room room) {
        String sql = "UPDATE quartos SET numero = ?, tipo = ?, preco_diaria = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, room.getNumber(), room.getRoomType(), room.getDailyPrice(), room.getIdRoom());
        return rowsAffected > 0;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM quartos WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
