package com.reservashoteis.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "DTO de resposta contendo dados da reserva")
public record ReservationResponseDto(
    @Schema(description = "ID único da reserva", example = "1")
    Long idReservation,

    @Schema(description = "ID do cliente que fez a reserva", example = "1")
    Long idClient,

    @Schema(description = "ID do quarto reservado", example = "1")
    Long idRoom,

    @Schema(description = "Data de check-in", example = "2024-12-01")
    LocalDate checkInDate,

    @Schema(description = "Data de check-out", example = "2024-12-05")
    LocalDate checkOutDate,

    @Schema(description = "Valor total da reserva", example = "1002.00")
    Float totalValue
) {}
