package com.reservashoteis.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "DTO de resposta com resumo das informações do cliente")
public record ClientSummaryResponseDto(
    @Schema(description = "Nome do cliente", example = "João Silva")
    String nome,

    @Schema(description = "Total de reservas feitas pelo cliente", example = "5")
    Long totalDeReservas,

    @Schema(description = "Valor total gasto pelo cliente", example = "2500.00")
    BigDecimal valorTotalGasto,

    @Schema(description = "Data da primeira reserva do cliente", example = "2024-01-15")
    LocalDate primeiraReserva
) {}