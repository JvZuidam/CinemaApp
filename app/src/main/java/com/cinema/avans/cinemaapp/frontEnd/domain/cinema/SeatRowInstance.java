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

    public SeatRowInstance() {

        this.hallInstance = null;
        this.seatRowInstanceId = 0;
        this.seatRowId = 0;
        this.rowNr = 0;
        this.seatInstances = new ArrayList<>();

    }

    // Returns the amount of seatInstances in the row
    public int getAmountOfSeats() {
        return seatInstances.size();

    }

    // Returns the amount of actual seats (excluding gaps)
    public int getAmountOfActualSeats() {

        int amountOfActualSeats = 0;

        for (SeatInstance seat : seatInstances) {

            if (seat.getStatus() != SeatStatus.GAP) {

                amountOfActualSeats ++;

            }

        }

        return amountOfActualSeats;

    }

    // Returns the amount of available seats
    public int getAmountOfAvailableSeats() {

        int amountOfAvailableSeats = 0;

        for (SeatInstance seatInstances : seatInstances) {

            if (seatInstances.getStatus() == SeatStatus.AVAILABLE) {

                amountOfAvailableSeats ++;

            }
        }

        return amountOfAvailableSeats;

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
