package com.reservashoteis.controller;

import com.reservashoteis.dto.request.ClientRequestDto;
import com.reservashoteis.services.client.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private ClientServiceInterface clientService;

    @PostMapping("")
    public void createClient(@RequestBody ClientRequestDto clientRequestDto) {
        clientService.createClient(clientRequestDto);
    }
}
