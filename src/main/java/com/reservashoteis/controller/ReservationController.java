package com.reservashoteis.controller;

import com.reservashoteis.dto.request.ReservationRequestDto;
import com.reservashoteis.model.Reservation;
import com.reservashoteis.services.reservations.ReservationServiceInterface;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Reservas", description = "Endpoints para gerenciamento de reservas")
@RestController
@RequestMapping("/api/v1/reservations")
@CrossOrigin(origins = "*")
public class ReservationController {

    @Autowired
    private ReservationServiceInterface reservationService;

    @Operation(
        summary = "Criar nova reserva",
        description = "Cria uma nova reserva no sistema e envia notificação por email.",
        security = @SecurityRequirement(name = "bearerAuth"),
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados da nova reserva",
            required = true,
            content = @Content(
                schema = @Schema(implementation = ReservationRequestDto.class),
                examples = @ExampleObject(value = "{\"idClient\":1,\"idRoom\":1,\"checkInDate\":\"2024-12-01\",\"checkOutDate\":\"2024-12-05\"}")
            )
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Reserva criada com sucesso",
                content = @Content(mediaType = "text/plain",
                    examples = @ExampleObject(value = "Reserva criada com sucesso.")
                )
            ),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Cliente ou quarto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody ReservationRequestDto dto) {
        reservationService.createReservation(dto);
        return ResponseEntity.ok("Reserva criada com sucesso.");
    }

    @Operation(
        summary = "Buscar reserva por ID",
        description = "Retorna os dados de uma reserva específica.",
        security = @SecurityRequirement(name = "bearerAuth"),
        responses = {
            @ApiResponse(responseCode = "200", description = "Reserva encontrada",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Reservation.class),
                    examples = @ExampleObject(value = "{\"idReservation\":1,\"idClient\":1,\"idRoom\":1,\"checkInDate\":\"2024-12-01\",\"checkOutDate\":\"2024-12-05\",\"totalValue\":1002.00}")
                )
            ),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(
        @Parameter(description = "ID da reserva", example = "1") @PathVariable Long id
    ) {
        Reservation reservation = reservationService.findReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    @Operation(
        summary = "Listar todas as reservas",
        description = "Retorna uma lista com todas as reservas cadastradas.",
        security = @SecurityRequirement(name = "bearerAuth"),
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de reservas retornada com sucesso",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Reservation.class)),
                    examples = @ExampleObject(value = "[{\"idReservation\":1,\"idClient\":1,\"idRoom\":1,\"checkInDate\":\"2024-12-01\",\"checkOutDate\":\"2024-12-05\",\"totalValue\":1002.00}]")
                )
            ),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.findAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @Operation(
        summary = "Atualizar reserva",
        description = "Atualiza os dados de uma reserva existente.",
        security = @SecurityRequirement(name = "bearerAuth"),
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados atualizados da reserva",
            required = true,
            content = @Content(
                schema = @Schema(implementation = ReservationRequestDto.class),
                examples = @ExampleObject(value = "{\"idClient\":1,\"idRoom\":2,\"checkInDate\":\"2024-12-02\",\"checkOutDate\":\"2024-12-06\"}")
            )
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Reserva atualizada com sucesso",
                content = @Content(mediaType = "text/plain",
                    examples = @ExampleObject(value = "Reserva atualizada com sucesso.")
                )
            ),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<String> updateReservation(
        @Parameter(description = "ID da reserva", example = "1") @PathVariable Long id,
        @RequestBody ReservationRequestDto dto
    ) {
        reservationService.updateReservation(id, dto);
        return ResponseEntity.ok("Reserva atualizada com sucesso.");
    }

    @Operation(
        summary = "Excluir reserva",
        description = "Exclui uma reserva do sistema.",
        security = @SecurityRequirement(name = "bearerAuth"),
        responses = {
            @ApiResponse(responseCode = "200", description = "Reserva excluída com sucesso",
                content = @Content(mediaType = "text/plain",
                    examples = @ExampleObject(value = "Reserva excluída com sucesso.")
                )
            ),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(
        @Parameter(description = "ID da reserva", example = "1") @PathVariable Long id
    ) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok("Reserva excluída com sucesso.");
    }
}
