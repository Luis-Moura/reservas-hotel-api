package com.reservashoteis.model;

public class Room {

    private Long idRoom;
    private int number;
    private String roomType;
    private Float dailyPrice;

    public Room(int number, String roomType, Float dailyPrice) {
        if (number <= 0) {
            throw new IllegalArgumentException("Room number must be greater than zero");
        }

        if (roomType == null || roomType.isEmpty()) {
            throw new IllegalArgumentException("Room type cannot be null or empty");
        }

        if (dailyPrice == null || dailyPrice <= 0) {
            throw new IllegalArgumentException("Daily price must be greater than zero");
        }

        this.number = number;
        this.roomType = roomType;
        this.dailyPrice = dailyPrice;
    }

    public Room(Long idRoom, int number, String roomType, Float dailyPrice) {
        if (idRoom != null && idRoom <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero");
        }

        if (number <= 0) {
            throw new IllegalArgumentException("Room number must be greater than zero");
        }

        if (roomType == null || roomType.isEmpty()) {
            throw new IllegalArgumentException("Room type cannot be null or empty");
        }

        if (dailyPrice == null || dailyPrice <= 0) {
            throw new IllegalArgumentException("Daily price must be greater than zero");
        }

        this.idRoom = idRoom;
        this.number = number;
        this.roomType = roomType;
        this.dailyPrice = dailyPrice;
    }

    public Long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Long idRoom) {
        if (idRoom != null && idRoom <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero");
        }
        this.idRoom = idRoom;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Room number must be greater than zero");
        }
        this.number = number;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        if (roomType == null || roomType.isEmpty()) {
            throw new IllegalArgumentException("Room type cannot be null or empty");
        }
        this.roomType = roomType;
    }

    public Float getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(Float dailyPrice) {
        if (dailyPrice == null || dailyPrice <= 0) {
            throw new IllegalArgumentException("Daily price must be greater than zero");
        }
        this.dailyPrice = dailyPrice;
    }
}
