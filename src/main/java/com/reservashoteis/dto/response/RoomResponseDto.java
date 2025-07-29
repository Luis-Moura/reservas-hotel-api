package com.reservashoteis.dto.response;

public record RoomResponseDto(
        Long id,
        int number,
        String roomType,
        Float dailyPrice) {

}
