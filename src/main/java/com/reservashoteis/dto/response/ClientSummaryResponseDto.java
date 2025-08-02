package com.reservashoteis.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ClientSummaryResponseDto(
    String nome,
    Long totalDeReservas,
    BigDecimal valorTotalGasto,
    LocalDate primeiraReserva
) {}