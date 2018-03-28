package com.cinema.avans.cinemaapp.frontEnd.domain.cinema;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class SeatRow {

    private Hall hall; // Done
    private int rowId; // Done
    private int rowNr; // Done

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public int getRowNr() {
        return rowNr;
    }

    public void setRowNr(int rowNr) {
        this.rowNr = rowNr;
    }
}
