package com.reservashoteis.dto.request;

public record RoomRequestDto(
    int number,
    String roomType,
    Float dailyPrice
) {
    public RoomRequestDto {
        if (number <= 0) {
            throw new IllegalArgumentException("Room number must be greater than zero");
        }

        if (roomType == null || roomType.isEmpty()) {
            throw new IllegalArgumentException("Room type cannot be null or empty");
        }

        if (dailyPrice == null || dailyPrice <= 0) {
            throw new IllegalArgumentException("Daily price must be greater than zero");
        }
    }
}
