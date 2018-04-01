package com.cinema.avans.cinemaapp.frontEnd.domain.cinema;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Hall implements Serializable {

    private int hallId; // Done
    private ArrayList<SeatRow> seatRows; // Done

    public Hall() {

        this.hallId = 0;
        this.seatRows = new ArrayList<>();

    }

    public int amountOfSeats() {

        int amountOfSeats = 0;

        for (SeatRow seatRow : seatRows) {

            amountOfSeats += seatRow.amountOfSeats();

        }

        return amountOfSeats;

    }

    // SETTERS
    public void setHallId(int hallId) {
        this.hallId = hallId;
    }
    public ArrayList<SeatRow> getSeatRows() {
        return seatRows;
    }

    // GETTERS
    public void setSeatRows(ArrayList<SeatRow> seatRows) {
        this.seatRows = seatRows;
    }
    public int getHallId() {
        return hallId;
    }

}
