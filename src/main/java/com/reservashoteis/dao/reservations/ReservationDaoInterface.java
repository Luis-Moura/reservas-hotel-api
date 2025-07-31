package com.reservashoteis.dao.reservations;

import com.reservashoteis.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationDaoInterface {

    void save(Reservation reservation);

    Optional<Reservation> findById(Long id);

    List<Reservation> findAll();

    boolean update(Reservation reservation);

    boolean delete(Long id);
}
