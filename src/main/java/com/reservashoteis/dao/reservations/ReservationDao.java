package com.reservashoteis.dao.reservations;

import com.reservashoteis.model.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservationDao implements ReservationDaoInterface {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReservationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Reservation> reservationMapper = (resultSet, rowNum) -> new Reservation(
        resultSet.getLong("id"),
        resultSet.getLong("cliente_id"),
        resultSet.getLong("quarto_id"),
        resultSet.getDate("data_checkin").toLocalDate(),
        resultSet.getDate("data_checkout").toLocalDate(),
        resultSet.getFloat("valor_total")
    );

    @Override
    public void save(Reservation reservation) {
        String sql = "INSERT INTO reservas (cliente_id, quarto_id, data_checkin, data_checkout, valor_total) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            reservation.getIdClient(),
            reservation.getIdRoom(),
            reservation.getCheckInDate(),
            reservation.getCheckOutDate(),
            reservation.getTotalValue()
        );
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        String sql = "SELECT * FROM reservas WHERE id = ?";
        List<Reservation> result = jdbcTemplate.query(sql, reservationMapper, id);
        return result.stream().findFirst();
    }

    @Override
    public List<Reservation> findAll() {
        String sql = "SELECT * FROM reservas";
        return jdbcTemplate.query(sql, reservationMapper);
    }

    @Override
    public boolean update(Reservation reservation) {
        String sql = "UPDATE reservas SET cliente_id = ?, quarto_id = ?, data_checkin = ?, data_checkout = ?, valor_total = ? WHERE id = ?";
        int rows = jdbcTemplate.update(sql,
            reservation.getIdClient(),
            reservation.getIdRoom(),
            reservation.getCheckInDate(),
            reservation.getCheckOutDate(),
            reservation.getTotalValue(),
            reservation.getIdReservation()
        );
        return rows > 0;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM reservas WHERE id = ?";
        int rows = jdbcTemplate.update(sql, id);
        return rows > 0;
    }
}
