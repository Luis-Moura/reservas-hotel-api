package com.reservashoteis.controller;

import com.reservashoteis.dto.request.ClientRequestDto;
import com.reservashoteis.dto.response.ClientResponseDto;
import com.reservashoteis.dto.response.ClientSummaryResponseDto;
import com.reservashoteis.model.Client;
import com.reservashoteis.services.client.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private ClientServiceInterface clientService;

    @PostMapping("")
    public ResponseEntity<Void> createClient(@RequestBody ClientRequestDto clientRequestDto) {
        clientService.createClient(clientRequestDto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> findClientById(@PathVariable Long id) {
        Client client = clientService.findClientById(id);

        ClientResponseDto response = new ClientResponseDto(
                client.getId(),
                client.getName(),
                client.getEmail());

        return ResponseEntity.ok(response);
    }

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

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClient(@PathVariable Long id, @RequestBody ClientRequestDto clientRequestDto) {
        clientService.updateClient(id, clientRequestDto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/summary")
    public ResponseEntity<List<ClientSummaryResponseDto>> getClientSummaries() {
        List<ClientSummaryResponseDto> summaries = clientService.findClientSummaries();
        return ResponseEntity.ok(summaries);
    }

}
