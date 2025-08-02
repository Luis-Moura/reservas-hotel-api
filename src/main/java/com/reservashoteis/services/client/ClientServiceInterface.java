package com.reservashoteis.services.client;

import com.reservashoteis.dto.request.ClientRequestDto;
import com.reservashoteis.dto.response.ClientSummaryResponseDto;
import com.reservashoteis.model.Client;

import java.util.List;

public interface ClientServiceInterface {
    void createClient(ClientRequestDto clientRequestDto);

    Client findClientById(Long id);

    List<Client> findAllClients();

    void updateClient(Long id, ClientRequestDto clientRequestDto);

    void deleteClient(Long id);

    List<ClientSummaryResponseDto> findClientSummaries();
}
