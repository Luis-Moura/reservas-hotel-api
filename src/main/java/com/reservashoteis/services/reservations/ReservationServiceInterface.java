package com.reservashoteis.services.reservations;

import com.reservashoteis.dto.request.ReservationRequestDto;
import com.reservashoteis.model.Reservation;

import java.util.List;

public interface ReservationServiceInterface {

    void createReservation(ReservationRequestDto reservationRequestDto);

    Reservation findReservationById(int id);

    List<Reservation> findAllReservations();

    void updateReservation(int id, ReservationRequestDto reservationRequestDto);

    void deleteReservation(int id);
}
