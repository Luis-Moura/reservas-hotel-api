package com.reservashoteis.dto.response;

import java.time.LocalDate;

public record ReservationResponseDto(
    Long idReservation,
    Long idClient,
    Long idRoom,
    LocalDate checkInDate,
    LocalDate checkOutDate,
    Float totalValue
) {}

