package com.reservashoteis.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de requisição para criação/atualização de quarto")
public record RoomRequestDto(
    @Schema(description = "Número do quarto", example = "101", required = true)
    int number,

    @Schema(description = "Tipo do quarto", example = "Suite Executiva", required = true)
    String roomType,

    @Schema(description = "Preço da diária", example = "250.50", required = true)
    Float dailyPrice
) {
    public RoomRequestDto {
        if (number <= 0) {
            throw new IllegalArgumentException("Room number must be greater than zero");
        }

        if (roomType == null || roomType.isEmpty()) {
            throw new IllegalArgumentException("Room type cannot be null or empty");
        }

        if (dailyPrice == null || dailyPrice <= 0) {
            throw new IllegalArgumentException("Daily price must be greater than zero");
        }
    }
}
