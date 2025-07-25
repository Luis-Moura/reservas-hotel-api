package com.reservashoteis.model;

public class Room {
    
    private int idRoom;
    private int number;
    private String roomType;
    private Float dailyPrice;

    public int getIdRoom() {
        return idRoom;
    }
    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getRoomType() {
        return roomType;
    }
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    public Float getDailyPrice() {
        return dailyPrice;
    }
    public void setDailyPrice(Float dailyPrice) {
        this.dailyPrice = dailyPrice;
    }
}
