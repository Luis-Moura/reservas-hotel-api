package com.reservashoteis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservashoteis.dto.request.RoomRequestDto;
import com.reservashoteis.dto.response.RoomResponseDto;
import com.reservashoteis.model.Room;
import com.reservashoteis.services.room.RoomServiceInterface;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;

@Tag(name = "Quartos", description = "Endpoints para gerenciamento de quartos")
@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin(origins = "*")
public class RoomController {
    @Autowired
    private RoomServiceInterface roomService;

    @Operation(
        summary = "Criar novo quarto",
        description = "Cria um novo quarto no sistema.",
        security = @SecurityRequirement(name = "bearerAuth"),
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados do novo quarto",
            required = true,
            content = @Content(
                schema = @Schema(implementation = RoomRequestDto.class),
                examples = @ExampleObject(value = "{\"number\":101,\"roomType\":\"Suite Executiva\",\"dailyPrice\":250.50}")
            )
        ),
        responses = {
            @ApiResponse(responseCode = "204", description = "Quarto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @PostMapping("")
    public ResponseEntity<Void> createRoom(@RequestBody RoomRequestDto roomRequestDto) {
        roomService.createRoom(roomRequestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
        summary = "Listar todos os quartos",
        description = "Retorna uma lista com todos os quartos cadastrados.",
        security = @SecurityRequirement(name = "bearerAuth"),
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de quartos retornada com sucesso",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = RoomResponseDto.class)),
                    examples = @ExampleObject(value = "[{\"id\":1,\"number\":101,\"roomType\":\"Suite Executiva\",\"dailyPrice\":250.50}]")
                )
            ),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @GetMapping("")
    public ResponseEntity<List<RoomResponseDto>> findAllRooms() {
        List<Room> rooms = roomService.findAllRooms();

        List<RoomResponseDto> response = rooms.stream().map(room -> new RoomResponseDto(
            room.getIdRoom(),
            room.getNumber(),
            room.getRoomType(),
            room.getDailyPrice()
        )).toList();

        return ResponseEntity.ok(response);
    }

    @Operation(
        summary = "Buscar quarto por ID",
        description = "Retorna os dados de um quarto específico.",
        security = @SecurityRequirement(name = "bearerAuth"),
        responses = {
            @ApiResponse(responseCode = "200", description = "Quarto encontrado",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RoomResponseDto.class),
                    examples = @ExampleObject(value = "{\"id\":1,\"number\":101,\"roomType\":\"Suite Executiva\",\"dailyPrice\":250.50}")
                )
            ),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDto> findRoomById(
        @Parameter(description = "ID do quarto", example = "1") @PathVariable Long id
    ) {
        Room room = roomService.findRoomById(id);

        RoomResponseDto response = new RoomResponseDto(
                room.getIdRoom(),
                room.getNumber(),
                room.getRoomType(),
                room.getDailyPrice());
        return ResponseEntity.ok(response);
    }

    @Operation(
        summary = "Atualizar quarto",
        description = "Atualiza os dados de um quarto existente.",
        security = @SecurityRequirement(name = "bearerAuth"),
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados atualizados do quarto",
            required = true,
            content = @Content(
                schema = @Schema(implementation = RoomRequestDto.class),
                examples = @ExampleObject(value = "{\"number\":102,\"roomType\":\"Suite Premium\",\"dailyPrice\":350.00}")
            )
        ),
        responses = {
            @ApiResponse(responseCode = "204", description = "Quarto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRoom(
        @Parameter(description = "ID do quarto", example = "1") @PathVariable Long id,
        @RequestBody RoomRequestDto roomRequestDto
    ) {
        roomService.updateRoom(id, roomRequestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
        summary = "Excluir quarto",
        description = "Exclui um quarto do sistema.",
        security = @SecurityRequirement(name = "bearerAuth"),
        responses = {
            @ApiResponse(responseCode = "204", description = "Quarto excluído com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(
        @Parameter(description = "ID do quarto", example = "1") @PathVariable Long id
    ) {
        roomService.deleteRoom(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
