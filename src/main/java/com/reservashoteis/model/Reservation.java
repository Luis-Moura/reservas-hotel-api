package com.reservashoteis.model;

import java.time.LocalDate;

public class Reservation {

    private int idReservation;
    private int idClient;
    private int idRoom;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Float totalValue;

    public Reservation(int idClient, int idRoom, LocalDate checkInDate, LocalDate checkOutDate, Float totalValue) {
        if (idClient <= 0) {
            throw new IllegalArgumentException("Client ID must be greater than zero");
        }

        if (idRoom <= 0) {
            throw new IllegalArgumentException("Room ID must be greater than zero");
        }

        if (checkInDate == null || checkOutDate == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }

        if (!checkOutDate.isAfter(checkInDate)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }

        if (totalValue == null || totalValue <= 0) {
            throw new IllegalArgumentException("Total value must be greater than zero");
        }

        this.idClient = idClient;
        this.idRoom = idRoom;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalValue = totalValue;
    }

    public Reservation(int idReservation, int idClient, int idRoom, LocalDate checkInDate, LocalDate checkOutDate, Float totalValue) {
        this(idClient, idRoom, checkInDate, checkOutDate, totalValue);
        if (idReservation <= 0) {
            throw new IllegalArgumentException("Reservation ID must be greater than zero");
        }
        this.idReservation = idReservation;
    }


    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        if (idReservation <= 0) {
            throw new IllegalArgumentException("Reservation ID must be greater than zero");
        }
        this.idReservation = idReservation;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        if (idClient <= 0) {
            throw new IllegalArgumentException("Client ID must be greater than zero");
        }
        this.idClient = idClient;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        if (idRoom <= 0) {
            throw new IllegalArgumentException("Room ID must be greater than zero");
        }
        this.idRoom = idRoom;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        if (checkInDate == null) {
            throw new IllegalArgumentException("Check-in date cannot be null");
        }
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        if (checkOutDate == null) {
            throw new IllegalArgumentException("Check-out date cannot be null");
        }
        if (!checkOutDate.isAfter(this.checkInDate)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }
        this.checkOutDate = checkOutDate;
    }

    public Float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Float totalValue) {
        if (totalValue == null || totalValue <= 0) {
            throw new IllegalArgumentException("Total value must be greater than zero");
        }
        this.totalValue = totalValue;
    }
}
