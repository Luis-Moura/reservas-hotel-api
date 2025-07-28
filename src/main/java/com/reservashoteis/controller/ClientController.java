package com.reservashoteis.controller;

import com.reservashoteis.dto.request.ClientRequestDto;
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
    public ResponseEntity<Client> findClientById(@PathVariable Long id) {
        Client response = clientService.findClientById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<List<Client>> findAllClients() {
        List<Client> response = clientService.findAllClients();

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
}
