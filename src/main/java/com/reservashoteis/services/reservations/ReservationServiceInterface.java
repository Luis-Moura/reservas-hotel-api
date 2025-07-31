package com.reservashoteis.services.reservations;

import com.reservashoteis.dto.request.ReservationRequestDto;
import com.reservashoteis.model.Reservation;

import java.util.List;

public interface ReservationServiceInterface {

    void createReservation(ReservationRequestDto reservationRequestDto);

    Reservation findReservationById(Long id);

    List<Reservation> findAllReservations();

    void updateReservation(Long id, ReservationRequestDto reservationRequestDto);

    void deleteReservation(Long id);
}
