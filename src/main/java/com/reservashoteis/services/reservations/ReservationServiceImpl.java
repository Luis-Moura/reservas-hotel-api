package com.reservashoteis.services.reservations;

import com.reservashoteis.dao.reservations.ReservationDaoInterface;
import com.reservashoteis.dao.room.RoomDaoInterface;
import com.reservashoteis.dto.request.ReservationRequestDto;
import com.reservashoteis.model.Reservation;
import com.reservashoteis.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationServiceInterface {

    @Autowired
    private ReservationDaoInterface reservationDao;

    @Autowired
    private RoomDaoInterface roomDao;

    @Override
    public void createReservation(ReservationRequestDto dto) {
        Reservation reservation = new Reservation(
            dto.idClient(),
            dto.idRoom(),
            dto.checkInDate(),
            dto.checkOutDate()
        );

        reservationDao.save(reservation);
    }

    @Override
    public Reservation findReservationById(Long id) {
        return reservationDao.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Reservation not found with id: " + id));
    }

    @Override
    public List<Reservation> findAllReservations() {
        List<Reservation> reservations = reservationDao.findAll();
        if (reservations.isEmpty()) {
            throw new IllegalArgumentException("No reservations found.");
        }
        return reservations;
    }

    @Override
    public void updateReservation(Long id, ReservationRequestDto dto) {
        Reservation existingReservation = reservationDao.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Reservation not found with id: " + id));

        Room room = roomDao.findById(dto.idRoom())
            .orElseThrow(() -> new IllegalArgumentException("Room not found with id: " + dto.idRoom()));

        long days = ChronoUnit.DAYS.between(dto.checkInDate(), dto.checkOutDate());
        float totalValue = room.getDailyPrice() * days;

        existingReservation.setIdClient(dto.idClient());
        existingReservation.setIdRoom(dto.idRoom());
        existingReservation.setCheckInDate(dto.checkInDate());
        existingReservation.setCheckOutDate(dto.checkOutDate());
        existingReservation.setTotalValue(totalValue);

        boolean updated = reservationDao.update(existingReservation);
        if (!updated) {
            throw new IllegalArgumentException("Failed to update reservation with id: " + id);
        }
    }

    @Override
    public void deleteReservation(Long id) {
        Reservation existingReservation = reservationDao.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Reservation not found with id: " + id));

        boolean deleted = reservationDao.delete(existingReservation.getIdReservation());
        if (!deleted) {
            throw new IllegalArgumentException("Failed to delete reservation with id: " + id);
        }
    }
}

