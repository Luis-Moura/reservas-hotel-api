package com.reservashoteis.dao.client;

import com.reservashoteis.dto.response.ClientSummaryResponseDto;
import com.reservashoteis.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientDaoInterface {
    void save(Client client);

    Optional<Client> findById(Long id);

    Optional<Client> findByEmail(String email);

    List<Client> findAll();

    boolean update(Client client);

    boolean delete(Long id);

    List<ClientSummaryResponseDto> findClientSummaries();
}
