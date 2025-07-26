package com.reservashoteis.dao.client;

import com.reservashoteis.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientDaoInterface {
    public void save(Client client);

    public Optional<Client> findById(Long id);

    public List<Client> findAll();

    public boolean update(Client client);

    public boolean delete(Long id);
}
