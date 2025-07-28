package com.reservashoteis.services.client;

import com.reservashoteis.dao.client.ClientDaoInterface;
import com.reservashoteis.dto.request.ClientRequestDto;
import com.reservashoteis.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientServiceInterface {
    @Autowired
    private ClientDaoInterface clientDao;

    @Override
    public void createClient(ClientRequestDto clientRequestDto) {
        Client client = new Client(clientRequestDto.name(), clientRequestDto.email());

        Client existingClient = clientDao.findByEmail(client.getEmail())
                .orElse(null);

        if (existingClient != null) {
            throw new IllegalArgumentException("Client with this email already exists.");
        }

        clientDao.save(client);
    }

    @Override
    public Client findClientById(Long id) {
        return clientDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + id));
    }

    @Override
    public List<Client> findAllClients() {
        return List.of();
    }

    @Override
    public void updateClient(Long id, ClientRequestDto clientRequestDto) {

    }

    @Override
    public void deleteClient(Long id) {

    }
}
