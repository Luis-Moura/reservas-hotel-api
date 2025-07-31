package com.reservashoteis.dto.request;

import java.time.LocalDate;

public record ReservationRequestDto(
    Long idClient,
    Long idRoom,
    LocalDate checkInDate,
    LocalDate checkOutDate,
    Float totalValue
) {
    public ReservationRequestDto {
        if (idClient <= 0) {
            throw new IllegalArgumentException("Client ID must be greater than zero");
        }
        if (idRoom <= 0) {
            throw new IllegalArgumentException("Room ID must be greater than zero");
        }
        if (checkInDate == null || checkOutDate == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        if (!checkOutDate.isAfter(checkInDate)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }
        if (totalValue == null || totalValue <= 0) {
            throw new IllegalArgumentException("Total value must be greater than zero");
        }
    }
}

