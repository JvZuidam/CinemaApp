package com.cinema.avans.cinemaapp.frontEnd.domain.cinema;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class SeatRowInstance implements Serializable {

    private HallInstance hallInstance; // Done
    private int seatRowInstanceId; // Done
    private int seatRowId; // Done
    private int rowNr; // Done
    private ArrayList<SeatInstance> seatInstances; // Done

    // Returns the amount of seatInstances in the row
    public int getAmountOfSeats() {
        return seatInstances.size();

    }

    // SETTERS
    public void setHallInstance(HallInstance hallInstance) {
        this.hallInstance = hallInstance;
    }
    public void setSeatRowInstanceId(int seatRowInstanceId) {
        this.seatRowInstanceId = seatRowInstanceId;
    }
    public void setSeatRowId(int seatRowId) {
        this.seatRowId = seatRowId;
    }
    public void setRowNr(int rowNr) {
        this.rowNr = rowNr;
    }
    public void setSeatInstances(ArrayList<SeatInstance> seatInstances) {
        this.seatInstances = seatInstances;
    }

    // GETTERS
    public HallInstance getHallInstance() {
        return hallInstance;
    }
    public int getSeatRowInstanceId() {
        return seatRowInstanceId;
    }
    public int getSeatRowId() {
        return seatRowId;
    }
    public ArrayList<SeatInstance> getSeatInstances() {
        return this.seatInstances;
    }
    public int getRowNr() {
        return rowNr;
    }

}
