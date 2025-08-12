package com.reservashoteis.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "DTO de requisição para criação de reserva")
public record ReservationRequestDto(
    @Schema(description = "ID do cliente que faz a reserva", example = "1", required = true)
    Long idClient,

    @Schema(description = "ID do quarto a ser reservado", example = "1", required = true)
    Long idRoom,

    @Schema(description = "Data de check-in", example = "2024-12-01", required = true)
    LocalDate checkInDate,

    @Schema(description = "Data de check-out", example = "2024-12-05", required = true)
    LocalDate checkOutDate
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
    }
}
