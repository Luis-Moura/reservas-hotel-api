package com.reservashoteis.dao.client;

import com.reservashoteis.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientDaoInterface {
    void save(Client client);

    Optional<Client> findById(Long id);

    List<Client> findAll();

    boolean update(Client client);

    boolean delete(Long id);
}
