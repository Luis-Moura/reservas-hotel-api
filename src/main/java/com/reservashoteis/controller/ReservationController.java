package com.reservashoteis.controller;

import com.reservashoteis.dto.request.ReservationRequestDto;
import com.reservashoteis.model.Reservation;
import com.reservashoteis.services.reservations.ReservationServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationServiceInterface reservationService;

    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody ReservationRequestDto dto) {
        reservationService.createReservation(dto);
        return ResponseEntity.ok("Reserva criada com sucesso.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.findReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.findAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReservation(@PathVariable Long id, @RequestBody ReservationRequestDto dto) {
        reservationService.updateReservation(id, dto);
        return ResponseEntity.ok("Reserva atualizada com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok("Reserva excluída com sucesso.");
    }
}
