package com.example.secondminiproject.dto;

import java.util.Date;

public class ReservationDate {

    private String imsiReservationDate;

    @Override
    public String toString() {
        return "ReservationDate{" +
                "imsiReservationDate='" + imsiReservationDate + '\'' +
                '}';
    }

    public String getImsiReservationDate() {
        return imsiReservationDate;
    }

    public void setImsiReservationDate(String imsiReservationDate) {
        this.imsiReservationDate = imsiReservationDate;
    }
}
