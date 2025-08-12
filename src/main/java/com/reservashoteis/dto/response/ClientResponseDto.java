package com.reservashoteis.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de resposta contendo dados do cliente")
public record ClientResponseDto(
    @Schema(description = "ID único do cliente", example = "1")
    Long id,

    @Schema(description = "Nome completo do cliente", example = "João Silva")
    String name,

    @Schema(description = "Email do cliente", example = "joao.silva@email.com")
    String email
) {
}
