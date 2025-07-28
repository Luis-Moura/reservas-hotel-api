package com.reservashoteis.dto.request;

public record ClientRequestDto(
        String name,
        String email
) {
    public ClientRequestDto {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email must be a valid email address");
        }
    }
}
