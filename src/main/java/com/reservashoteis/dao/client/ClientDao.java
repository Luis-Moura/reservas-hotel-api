package com.reservashoteis.dao.client;

import com.reservashoteis.model.Client;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientDao implements ClientDaoInterface {
    private final JdbcTemplate jdbcTemplate;

    public ClientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Client> clientMapper = (resultSet, rowNum) ->
            new Client(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email")
            );

    @Override
    public void save(Client client) {
        String sql = "INSERT INTO clients (name, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, client.getName(), client.getEmail());
    }

    @Override
    public Optional<Client> findById(Long id) {
        String sql = "SELECT * FROM clients WHERE id = ?";
        List<Client> results = jdbcTemplate.query(sql, clientMapper, id);
        return results.stream().findFirst();
    }

    @Override
    public List<Client> findAll() {
        String sql = "SELECT * FROM clients";
        return jdbcTemplate.query(sql, clientMapper);
    }

    @Override
    public boolean update(Client client) {
        String sql = "UPDATE clients SET name = ?, email = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, client.getName(), client.getEmail(), client.getId());
        return rowsAffected > 0;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM clients WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
