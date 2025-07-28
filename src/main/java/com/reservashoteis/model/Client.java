package com.reservashoteis.model;

public class Client {
    private long id;
    private final String name;
    private final String email;

    public Client(String name, String email) {
        if (name == null || name.isEmpty() || email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email must contain '@'");
        }

        this.name = name;
        this.email = email;
    }

    public Client(long id, String name, String email) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero");
        }

        if (name == null || name.isEmpty() || email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email must contain '@'");
        }

        this.id = id;
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
