package com.cinema.avans.cinemaapp.frontEnd.domain.cinema;

import java.io.Serializable;
import java.util.ArrayList;

public class HallInstance implements Serializable {

    private int hallInstanceId; // Done
    private int hallId; // Done
    private ArrayList<SeatRowInstance> rows; // Done

    // Returns the max. amount of seats in a row
    public int amountOfSeatsInARow() {

        int mostRowSeats = 0;

        for (SeatRowInstance seatRowInstance : rows) {

            int currentRowSeats = seatRowInstance.getAmountOfSeats();

            if (currentRowSeats > mostRowSeats) {
                mostRowSeats = currentRowSeats;

            }

        }

        return mostRowSeats;

    }

    // SETTERS
    public void setHallInstanceId(int hallInstanceId) {
        this.hallInstanceId = hallInstanceId;
    }
    public void setHallId(int hallId) {
        this.hallId = hallId;
    }
    public void setRows(ArrayList<SeatRowInstance> rows) {
        this.rows = rows;
    }
    // GETTERS
    public int getHallInstanceId() {
        return hallInstanceId;
    }
    public int getHallId() {
        return hallId;
    }
    public ArrayList<SeatRowInstance> getRows() {
        return this.rows;
    }

}
