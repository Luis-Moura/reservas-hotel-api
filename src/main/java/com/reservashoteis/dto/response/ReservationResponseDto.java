package com.reservashoteis.dto.response;

import java.time.LocalDate;

public record ReservationResponseDto(
    int idReservation,
    int idClient,
    int idRoom,
    LocalDate checkInDate,
    LocalDate checkOutDate,
    Float totalValue
) {}

