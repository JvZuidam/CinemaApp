package com.cinema.avans.cinemaapp.frontEnd.domain.cinema;

import java.io.Serializable;
import java.util.ArrayList;

public class CinemaHall implements Serializable {

    private int hallId;
    private ArrayList<SeatRow> rows;

    // Returns the max. amount of seats in a row
    public int amountOfSeatsInARow() {

        int mostRowSeats = 0;

        for (SeatRow seatRow : rows) {

            int currentRowSeats = seatRow.getAmountOfSeats();

            if (currentRowSeats > mostRowSeats) {
                mostRowSeats = currentRowSeats;

            }

        }

        return mostRowSeats;

    }

    // SETTERS
    public void setHallId(int hallId) {
        this.hallId = hallId;
    }
    public void setRows(ArrayList<SeatRow> rows) {
        this.rows = rows;
    }
    // GETTERS
    public int getHallId() {
        return hallId;
    }
    public ArrayList<SeatRow> getRows() {
        return this.rows;
    }

}
