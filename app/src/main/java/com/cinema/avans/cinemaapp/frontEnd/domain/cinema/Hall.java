package com.cinema.avans.cinemaapp.frontEnd.domain.cinema;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Hall implements Serializable {

    private int hallId; // Done
    private int hallNr; // Done
    private ArrayList<SeatRow> seatRows; // Done

    public Hall() {

        this.hallId = 0;
        this.hallNr = 0;
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
    public void setHallNr(int hallNr) {
        this.hallNr = hallNr;
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
    public int getHallNr() {
        return hallNr;
    }

    @Override
    public String toString() {

        return "Hall: " + hallId + "\n" +
                "- HallNr: " + hallNr + "\n" +
                "- Amount of rows: " + seatRows.size() + "\n" +
                "- Amount of seats: " + amountOfSeats();

    }

}
