package com.reservashoteis.controller;

import com.reservashoteis.dto.request.ClientRequestDto;
import com.reservashoteis.dto.response.ClientResponseDto;
import com.reservashoteis.dto.response.ClientSummaryResponseDto;
import com.reservashoteis.model.Client;
import com.reservashoteis.services.client.ClientServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "Endpoints para gerenciamento de clientes")
@RestController()
@RequestMapping("/api/v1/clients")
public class ClientController {
    @Autowired
    private ClientServiceInterface clientService;

    @Operation(
        summary = "Criar novo cliente",
        description = "Cria um novo cliente no sistema.",
        security = @SecurityRequirement(name = "bearerAuth"),
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados do novo cliente",
            required = true,
            content = @Content(
                schema = @Schema(implementation = ClientRequestDto.class),
                examples = @ExampleObject(value = "{\"name\":\"João Silva\",\"email\":\"joao.silva@email.com\",\"contact\":\"(11) 99999-9999\"}")
            )
        ),
        responses = {
            @ApiResponse(responseCode = "204", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @PostMapping("")
    public ResponseEntity<Void> createClient(@RequestBody ClientRequestDto clientRequestDto) {
        clientService.createClient(clientRequestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
        summary = "Buscar cliente por ID",
        description = "Retorna os dados de um cliente específico.",
        security = @SecurityRequirement(name = "bearerAuth"),
        responses = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClientResponseDto.class),
                    examples = @ExampleObject(value = "{\"id\":1,\"name\":\"João Silva\",\"email\":\"joao.silva@email.com\",\"contact\":\"(11) 99999-9999\"}")
                )
            ),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> findClientById(
        @Parameter(description = "ID do cliente", example = "1") @PathVariable Long id
    ) {
        Client client = clientService.findClientById(id);

        ClientResponseDto response = new ClientResponseDto(
                client.getId(),
                client.getName(),
                client.getEmail());

        return ResponseEntity.ok(response);
    }

    @Operation(
        summary = "Listar todos os clientes",
        description = "Retorna uma lista com todos os clientes cadastrados.",
        security = @SecurityRequirement(name = "bearerAuth"),
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ClientResponseDto.class)),
                    examples = @ExampleObject(value = "[{\"id\":1,\"name\":\"João Silva\",\"email\":\"joao.silva@email.com\",\"contact\":\"(11) 99999-9999\"}]")
                )
            ),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @GetMapping("")
    public ResponseEntity<List<ClientResponseDto>> findAllClients() {
        List<Client> clients = clientService.findAllClients();

        List<ClientResponseDto> response = clients.stream()
                .map(client -> new ClientResponseDto(
                        client.getId(),
                        client.getName(),
                        client.getEmail()))
                .toList();

        return ResponseEntity.ok(response);
    }

    @Operation(
        summary = "Atualizar cliente",
        description = "Atualiza os dados de um cliente existente.",
        security = @SecurityRequirement(name = "bearerAuth"),
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados atualizados do cliente",
            required = true,
            content = @Content(
                schema = @Schema(implementation = ClientRequestDto.class),
                examples = @ExampleObject(value = "{\"name\":\"João Silva Atualizado\",\"email\":\"joao.novo@email.com\",\"contact\":\"(11) 88888-8888\"}")
            )
        ),
        responses = {
            @ApiResponse(responseCode = "204", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClient(
        @Parameter(description = "ID do cliente", example = "1") @PathVariable Long id,
        @RequestBody ClientRequestDto clientRequestDto
    ) {
        clientService.updateClient(id, clientRequestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
        summary = "Excluir cliente",
        description = "Exclui um cliente do sistema.",
        security = @SecurityRequirement(name = "bearerAuth"),
        responses = {
            @ApiResponse(responseCode = "204", description = "Cliente excluído com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(
        @Parameter(description = "ID do cliente", example = "1") @PathVariable Long id
    ) {
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
        summary = "Obter resumo dos clientes",
        description = "Retorna um resumo com informações dos clientes.",
        security = @SecurityRequirement(name = "bearerAuth"),
        responses = {
            @ApiResponse(responseCode = "200", description = "Resumo dos clientes retornado com sucesso",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ClientSummaryResponseDto.class))
                )
            ),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    @GetMapping("/summary")
    public ResponseEntity<List<ClientSummaryResponseDto>> getClientSummaries() {
        List<ClientSummaryResponseDto> summaries = clientService.findClientSummaries();
        return ResponseEntity.ok(summaries);
    }
}
