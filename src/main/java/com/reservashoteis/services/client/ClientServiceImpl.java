package com.reservashoteis.services.client;

import com.reservashoteis.dao.client.ClientDaoInterface;
import com.reservashoteis.dto.request.ClientRequestDto;
import com.reservashoteis.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        List<Client> clients = clientDao.findAll();

        if (clients.isEmpty()) {
            throw new IllegalArgumentException("No clients found.");
        }

        return clients;
    }

    @Override
    public void updateClient(Long id, ClientRequestDto clientRequestDto) {
        Client existingClient = clientDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + id));

        if (Objects.equals(existingClient.getName(), clientRequestDto.name())) {
            throw new IllegalArgumentException("Cannot update client with the same name.");
        }

        if (Objects.equals(existingClient.getEmail(), clientRequestDto.email())) {
            throw new IllegalArgumentException("Cannot update client with the same email.");
        }

        existingClient.setName(clientRequestDto.name());
        existingClient.setEmail(clientRequestDto.email());

        boolean updated = clientDao.update(existingClient);

        if (!updated) {
            throw new IllegalArgumentException("Failed to update client with id: " + id);
        }
    }

    @Override
    public void deleteClient(Long id) {
        Client existingClient = clientDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + id));

        boolean deleted = clientDao.delete(existingClient.getId());

        if (!deleted) {
            throw new IllegalArgumentException("Failed to delete client with id: " + id);
        }
    }
}
