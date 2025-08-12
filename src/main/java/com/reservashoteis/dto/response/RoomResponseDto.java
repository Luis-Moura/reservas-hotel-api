package com.reservashoteis.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de resposta contendo dados do quarto")
public record RoomResponseDto(
    @Schema(description = "ID único do quarto", example = "1")
    Long id,

    @Schema(description = "Número do quarto", example = "101")
    int number,

    @Schema(description = "Tipo do quarto", example = "Suite Executiva")
    String roomType,

    @Schema(description = "Preço da diária", example = "250.50")
    Float dailyPrice
) {
}
