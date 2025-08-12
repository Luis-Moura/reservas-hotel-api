package com.reservashoteis.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de requisição para criação/atualização de cliente")
public record ClientRequestDto(
        @Schema(description = "Nome completo do cliente", example = "João Silva", required = true)
        String name,

        @Schema(description = "Email do cliente", example = "joao.silva@email.com", required = true)
        String email
) {
    public ClientRequestDto {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email must be a valid email address");
        }
    }
}
